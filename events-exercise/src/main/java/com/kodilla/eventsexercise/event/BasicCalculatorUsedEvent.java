package com.kodilla.eventsexercise.event;

import org.springframework.context.ApplicationEvent;

public class BasicCalculatorUsedEvent extends ApplicationEvent {

    private String a;
    private String b;
    private String operator;

    public BasicCalculatorUsedEvent(Object source, String a, String b, String operator) {
        super(source);
        this.a = a;
        this.b = b;
        this.operator = operator;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getOperator() {
        return operator;
    }
}
