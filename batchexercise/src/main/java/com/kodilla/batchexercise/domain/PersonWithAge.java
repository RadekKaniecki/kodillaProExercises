package com.kodilla.batchexercise.domain;

public class PersonWithAge extends BasePerson {
    private int age;

    public PersonWithAge() {
    }

    public PersonWithAge(int id, String firstName, String lastName, int age) {
        super(id, firstName, lastName);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
