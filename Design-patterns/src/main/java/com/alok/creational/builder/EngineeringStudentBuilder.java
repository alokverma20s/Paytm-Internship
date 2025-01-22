package com.alok.creational.builder;

import java.util.ArrayList;
import java.util.List;

public class EngineeringStudentBuilder extends StudentBuilder {
    @Override
    public StudentBuilder setSubjects() {
        List<String> subs = new ArrayList<>();
        subs.add("Engineering Subject 1");
        subs.add("Engineering Subject 2");
        subs.add("Engineering Subject 3");
        subs.add("Engineering Subject 4");
        this.subjects = subs;
        return this;
    }
}
