package com.alok.problem5;

import java.util.List;

public class Student {
    private String name;
    private int age;
    private List<Double> grades;

    public Student(String name, int age, List<Double> grades) {
        this.name = name;
        this.age = age;
        this.grades = grades;
    }

    public double calculateAverageGrade() {
        if (grades == null || grades.isEmpty()) {
            System.out.println("No grades available to calculate average.");
            return 0;
        }
        double total = 0;
        for (double grade : grades) {
            total += grade;
        }
        return total / grades.size();
    }

    public void displayStudentDetails() {
        System.out.printf("Name: %s, Age: %d, Average Grade: %.2f%n", name, age, calculateAverageGrade());
    }

    public static void main(String[] args) {
        List<Double> grades = List.of(85.0, 90.5, 78.0, 92.0);
        Student student = new Student("Jane Doe", 20, grades);
        student.displayStudentDetails();
    }
}