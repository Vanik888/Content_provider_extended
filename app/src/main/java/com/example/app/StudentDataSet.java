package com.example.app;

/**
 * Created by vanik on 16.04.14.
 */
public class StudentDataSet {
    private String name;
    private String surname;
    private int age;
    public StudentDataSet(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public int getAge() {
        return age;
    }
}
