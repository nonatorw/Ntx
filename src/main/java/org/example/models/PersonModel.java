package org.example.models;

import java.util.Objects;

public class PersonModel {
    private final int id;
    private final String name;
    private final String gender;
    private final int age;
    private final String date;
    private String country;

    public PersonModel(int id, String name, String gender, int age, String date, String country) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.date = date;
        this.country = country;
    }

    public boolean notChild() {
        return age >= 18;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getDate() {
        return date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonModel that = (PersonModel) o;
        return id == that.id && age == that.age && Objects.equals(name, that.name) && Objects.equals(gender, that.gender) && Objects.equals(date, that.date) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, age, date, country);
    }

    @Override
    public String toString() {
        return "PersonModel{" + "id=" + id + ", name='" + name + '\'' + ", gender='" + gender + '\'' + ", age=" + age + ", date='" + date + '\'' + ", country='" + country + '\'' + '}';
    }
}
