package com.kodilla.eventsexercise.domain;

public class BasicCalculator {
    private String a;
    private String b;
    private String operator;

    public BasicCalculator(String a, String b, String operator) {
        this.a = a;
        this.b = b;
        this.operator = operator;
    }

    public String getResult() {
        switch (this.operator) {
            case "add":
                return String.valueOf(Double.valueOf(this.a) + Double.valueOf(this.b));
            case "subtract":
                return String.valueOf(Double.valueOf(this.a) - Double.valueOf(this.b));
            case "multiply":
                return String.valueOf(Double.valueOf(this.a) * Double.valueOf(this.b));
            case "divide":
                return String.valueOf(Double.valueOf(this.a) / Double.valueOf(this.b));
            default:
                return "Something went wrong";
        }
    }
}
