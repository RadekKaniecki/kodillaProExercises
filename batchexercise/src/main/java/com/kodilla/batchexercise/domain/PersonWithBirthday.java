package com.kodilla.batchexercise.domain;

import java.time.LocalDate;

public class PersonWithBirthday extends BasePerson {
    private String birthDay;

    public PersonWithBirthday() {
    }

    public PersonWithBirthday(int id, String firstName, String lastName, String birthDay) {
        super(id, firstName, lastName);
        this.birthDay = birthDay;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
}
