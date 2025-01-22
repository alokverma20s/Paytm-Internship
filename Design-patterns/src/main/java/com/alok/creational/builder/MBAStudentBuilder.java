package com.alok.creational.builder;

import java.util.ArrayList;
import java.util.List;

public class MBAStudentBuilder extends StudentBuilder{
    @Override
    public StudentBuilder setSubjects() {
        List<String> subs = new ArrayList<>();
        subs.add("MBA Subject 1");
        subs.add("MBA Subject 2");
        subs.add("MBA Subject 3");
        subs.add("MBA Subject 4");
        this.subjects = subs;
        return this;
    }
}
