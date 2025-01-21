package com.alok.builder;

public class Director {
    StudentBuilder studentBuilder;

    Director(StudentBuilder studentBuilder){
        this.studentBuilder = studentBuilder;
    }

    public Student createStudent(){
        if(studentBuilder instanceof EngineeringStudentBuilder){
            return createEngineeringStudent();
        }
        else if(studentBuilder instanceof  MBAStudentBuilder){
            return createMBAStudent();
        }
        return null;
    }

    private Student createEngineeringStudent(){
        return studentBuilder
                .setRollNo(1)
                .setAge(22)
                .setName("Alok")
                .setFatherName("ABC")
                .setMotherName("DEF")
                .setSubjects().build();
    }
    private Student createMBAStudent(){
        return studentBuilder
                .setRollNo(2)
                .setAge(24)
                .setName("NAME")
                .setFatherName("Father's Name")
                .setMotherName("Mother's Name")
                .setSubjects()
                .build();
    }
}
