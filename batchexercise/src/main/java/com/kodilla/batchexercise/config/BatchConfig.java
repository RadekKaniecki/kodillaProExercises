package com.kodilla.batchexercise.config;

import com.kodilla.batchexercise.domain.PersonWithAge;
import com.kodilla.batchexercise.domain.PersonWithBirthday;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    PersonProcessor processor() {
        return new PersonProcessor();
    }

    @Bean
    FlatFileItemReader<PersonWithBirthday> reader() {
        FlatFileItemReader<PersonWithBirthday> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("dates_input.csv"));

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "firstName", "lastName", "birthDay");

        BeanWrapperFieldSetMapper<PersonWithBirthday> mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(PersonWithBirthday.class);

        DefaultLineMapper<PersonWithBirthday> lineMapper = new DefaultLineMapper<>();
        lineMapper.setFieldSetMapper(mapper);
        lineMapper.setLineTokenizer(tokenizer);

        reader.setLineMapper(lineMapper);

        return reader;
    }

    @Bean
    FlatFileItemWriter<PersonWithAge> writer() {
        FlatFileItemWriter<PersonWithAge> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("age_output.csv"));

        BeanWrapperFieldExtractor<PersonWithAge> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] {"id", "firstName", "lastName", "age"});

        DelimitedLineAggregator<PersonWithAge> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setFieldExtractor(fieldExtractor);
        lineAggregator.setDelimiter(",");

        writer.setLineAggregator(lineAggregator);
        writer.setShouldDeleteIfExists(true);

        return writer;
    }

    @Bean
    Step priceChange(ItemReader<PersonWithBirthday> reader, ItemProcessor<PersonWithBirthday, PersonWithAge> processor, ItemWriter<PersonWithAge> writer) {
        return stepBuilderFactory.get("ageCalculator")
                .<PersonWithBirthday, PersonWithAge>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    Job changePriceJob(Step priceChange) {
        return jobBuilderFactory.get("ageCalculatorJob")
                .incrementer(new RunIdIncrementer())
                .flow(priceChange)
                .end()
                .build();
    }


}
