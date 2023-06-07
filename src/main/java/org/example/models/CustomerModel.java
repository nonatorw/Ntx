package org.example.models;

import java.util.Objects;

public class CustomerModel {
    private final String company;
    private final String country;
    private final String email;

    public CustomerModel(String company, String country, String email) {
        this.company = company;
        this.country = country;
        this.email = email;
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
        return Objects.equals(company, that.company) && Objects.equals(country, that.country) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, country, email);
    }

    @Override
    public String toString() {
        return "CustomerModel{" + "company='" + company + '\'' + ", country='" + country + '\'' + ", email='" + email + '\'' + '}';
    }
}
