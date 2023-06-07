package org.example;

import org.example.models.CustomerModel;
import org.example.models.JoinModel;
import org.example.models.PersonModel;
import org.example.repositories.load.CustomersLoadRepository;
import org.example.repositories.load.PeopleLoadRepository;
import org.example.repositories.write.JoinRepository;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final PeopleLoadRepository peopleRepository = new PeopleLoadRepository();
    private static final CustomersLoadRepository customersLoadRepository = new CustomersLoadRepository();
    private static final JoinRepository joinRepository = new JoinRepository();

    public static void main(String[] args) {

        //! Join
        List<CustomerModel> customerList = customersLoadRepository.load();
        List<JoinModel> joinList = peopleRepository.load().stream().map(personModel -> new JoinModel(personModel, null)).collect(Collectors.toList());

        //! write the join
        joinRepository.save(joinList);

        //! PersonModel{id=0, name='', gender='', age=0, date='', country=''}
        System.out.println(new PersonModel(0, "", "", 0, "", ""));
    }
}