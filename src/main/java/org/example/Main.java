package org.example;

import org.example.models.CustomerModel;
import org.example.models.JoinModel;
import org.example.models.PersonModel;
import org.example.repositories.load.CustomersLoadRepository;
import org.example.repositories.load.PeopleLoadRepository;
import org.example.repositories.write.JoinRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Main {

    private static final PeopleLoadRepository peopleRepository = new PeopleLoadRepository();
    private static final CustomersLoadRepository customersLoadRepository = new CustomersLoadRepository();
    private static final JoinRepository joinRepository = new JoinRepository();

    public static void main(String[] args) {
        //! Join
        List<PersonModel> peopleList = peopleRepository.load();
        List<CustomerModel> customerList = customersLoadRepository.load();

        List<JoinModel> joinList = leftJoin(peopleList, customerList);

        //! PersonModel{id=0, name='', gender='', age=0, date='', country=''}
        joinList.forEach(System.out::println);
    }

    public static List<JoinModel> leftJoin(List<PersonModel> peopleList, List<CustomerModel> customerList) {

        // Sorting lists by Country.
        peopleList.sort(Comparator.comparing(PersonModel::getCountry));
        customerList.sort(Comparator.comparing(CustomerModel::getCountry));

        System.out.println(peopleList.size());
        System.out.println(customerList.size());

        Map<String, List<CustomerModel>> custMap = customerList.stream()
                .collect(groupingBy(CustomerModel::getCountry));

        List<JoinModel> joinList = peopleList.stream()
                .filter(p -> Objects.nonNull(p.getAge()) && p.getAge() >= 18)
                .flatMap(p -> Optional.ofNullable(custMap.get(p.getCountry()))
                        .filter(c -> c.stream().anyMatch(c1 -> !c1.getName().equals(p.getName())))
                        .map(c -> Stream.of(new JoinModel(p, c.get(0))))
                        .orElse(Stream.of(new JoinModel(p, null))))
                .sorted(Comparator.comparing(JoinModel::getId))
                .collect(Collectors.toList());

        List<JoinModel> joinList2 = peopleList.stream()
                .filter(p -> Objects.nonNull(p.getAge()) && p.getAge() >= 18)
                .flatMap(p -> Optional.ofNullable(custMap.get(p.getCountry()))
                        .map(c -> Stream.of(new JoinModel(p, c.get(0))))
                        .orElse(Stream.of(new JoinModel(p, null))))
                .sorted(Comparator.comparing(JoinModel::getId))
                .collect(Collectors.toList());

        /*
        peopleList.forEach(p -> System.out.println(p.getCountry()));
        peopleList.forEach(System.out::println);
        System.out.println(peopleSize.size());
         */

        /*
        customerList.forEach(c -> System.out.println(c.getCountry()));
        customerList.forEach(System.out::println);

        List<CustomerModel> country = customerList.stream()
                .filter(c -> c.getCountry().contains("United Kingdom"))
                .collect(toList());
        country.forEach(System.out::println);
        System.out.println(country.size());
         */

        /*
        joinList.forEach(System.out::println);
        System.out.println(joinList.size());
         */

        /*
        List<JoinModel> diff = new ArrayList<>(joinList);
        diff.removeAll(joinList2);
        System.out.println("Diff Size: " + diff.size());
        diff.forEach(System.out::println);
         */
       return joinList;
    }
}