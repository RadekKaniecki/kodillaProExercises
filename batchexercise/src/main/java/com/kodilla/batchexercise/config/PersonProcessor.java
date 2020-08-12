package com.kodilla.batchexercise.config;

import com.kodilla.batchexercise.domain.PersonWithAge;
import com.kodilla.batchexercise.domain.PersonWithBirthday;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonProcessor implements ItemProcessor<PersonWithBirthday, PersonWithAge> {

    @Override
    public PersonWithAge process(PersonWithBirthday item) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDay = LocalDate.parse(item.getBirthDay(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        int age = currentDate.getYear() - birthDay.getYear();

        return new PersonWithAge(item.getId(), item.getFirstName(), item.getLastName(), age);
    }
}
