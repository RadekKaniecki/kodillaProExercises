package com.kodilla.eventsexercise.controller;

import com.kodilla.eventsexercise.domain.BasicCalculator;
import com.kodilla.eventsexercise.event.BasicCalculatorUsedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/calculator")
public class RestCalculator implements ApplicationEventPublisherAware {

    private final String ADD = "add";
    private final String SUBTRACT = "subtract";
    private final String MULTIPLY = "multiply";
    private final String DIVIDE = "divide";

    private ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @GetMapping("/add")
    public String add(@RequestParam String a, @RequestParam String b) {
        BasicCalculator calculator = new BasicCalculator(a, b, ADD);
        eventPublisher.publishEvent(new BasicCalculatorUsedEvent(this, a, b, ADD));
        return calculator.getResult();
    }

    @GetMapping("/subtract")
    public String subtract(@RequestParam String a, @RequestParam String b) {
        BasicCalculator calculator = new BasicCalculator(a, b, SUBTRACT);
        eventPublisher.publishEvent(new BasicCalculatorUsedEvent(this, a, b, SUBTRACT));
        return calculator.getResult();
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam String a, @RequestParam String b) {
        BasicCalculator calculator = new BasicCalculator(a, b, MULTIPLY);
        eventPublisher.publishEvent(new BasicCalculatorUsedEvent(this, a, b, MULTIPLY));
        return calculator.getResult();
    }

    @GetMapping("/divide")
    public String divide(@RequestParam String a, @RequestParam String b) {
        if (!b.equals("0")) {
            BasicCalculator calculator = new BasicCalculator(a, b, DIVIDE);
            eventPublisher.publishEvent(new BasicCalculatorUsedEvent(this, a, b, DIVIDE));
            return calculator.getResult();
        } else {
            return "Cannot divide by 0";
        }
    }
}
