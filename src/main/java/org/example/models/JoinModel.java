package org.example.models;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class JoinModel {
    private static final String EMPTY = "";

    private final int id;
    private final String name;
    private final String gender;
    private final int age;
    private final String date;
    private final String country;
    private final String company;
    private final String email;

    //! Left table (people) and the corresponding records from the right table (customers)
    public JoinModel(@NotNull PersonModel personModel, CustomerModel customerModel) {
        if (Objects.nonNull(customerModel)) {
            //  PersonModel copy
            this.id = personModel.getId();
            this.name = personModel.getName();
            this.gender = personModel.getGender();
            this.age = personModel.getAge();
            this.date = personModel.getDate();
            //  CustomerModel copy
            this.country = customerModel.getCountry();
            this.company = customerModel.getCompany();
            this.email = customerModel.getEmail();
        } else {
            //  PersonModel copy
            this.id = personModel.getId();
            this.name = personModel.getName();
            this.gender = personModel.getGender();
            this.age = personModel.getAge();
            this.date = personModel.getDate();
            //  CustomerModel empty
            this.country = EMPTY;
            this.company = EMPTY;
            this.email = EMPTY;
        }
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

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinModel joinModel = (JoinModel) o;
        return id == joinModel.id && age == joinModel.age && Objects.equals(name, joinModel.name) && Objects.equals(gender, joinModel.gender) && Objects.equals(date, joinModel.date) && Objects.equals(country, joinModel.country) && Objects.equals(company, joinModel.company) && Objects.equals(email, joinModel.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, age, date, country, company, email);
    }

    @Override
    public String toString() {
        return "JoinModel{" + "id=" + id + ", name='" + name + '\'' + ", gender='" + gender + '\'' + ", age=" + age + ", date='" + date + '\'' + ", country='" + country + '\'' + ", company='" + company + '\'' + ", email='" + email + '\'' + '}';
    }
}
