package com.alok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class FileIOExceptionHandling {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(FileIOExceptionHandling.class.getClassLoader().getResourceAsStream("example.txt"))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("An exception occurred: " + e.getMessage());
        }
    }
}
