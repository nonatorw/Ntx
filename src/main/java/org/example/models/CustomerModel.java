package org.example.models;

import java.util.Objects;

public class CustomerModel {
    private final String name;
    private final String company;
    private final String country;
    private final String email;

    public CustomerModel(String name, String company, String country, String email) {
        this.name = name;
        this.company = company;
        this.country = country;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerModel that = (CustomerModel) o;
        return Objects.equals(name, that.name) && Objects.equals(company, that.company) && Objects.equals(country, that.country) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, company, country, email);
    }

    @Override
    public String toString() {
        return "CustomerModel{" + "name='" + name + '\'' + "company='" + company + '\'' + ", country='" + country + '\'' + ", email='" + email + '\'' + '}';
    }
}
