package com.citizen.service.concretes;

import com.citizen.domain.concretes.Person;
import com.citizen.repository.abstracts.PersonDao;
import com.citizen.results.DataResult;
import com.citizen.results.Result;
import com.citizen.results.SuccessDataResult;
import com.citizen.service.abstracts.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonManager implements PersonService {

    @Autowired
    private PersonDao personDao;

    /*@Override
    public DataResult<List<Person>> getAll() {
        List<Person> personList = this.personDao.findAll();
        return new SuccessDataResult<>(personList, "İyisin");
    }*/
    @Override
    public DataResult<List<PersonResponse>> getAll() {
        List<Person> personList = this.personDao.findAll();

        List<PersonResponse> personResponseList = mapPersonToPersonResponseList(personList);

        return new SuccessDataResult<>(personResponseList, "Successfully retrieved persons.");
    }

    @Override
    public DataResult<List<PersonResponse>> findByIsCitizen(Boolean isCitizen) {
        List<Person> personList = this.personDao.findByIsCitizen(isCitizen);

        List<PersonResponse> personResponseList = mapPersonToPersonResponseList(personList);

        return new SuccessDataResult<>(personResponseList, "Successfully retrieved persons.");
    }

    @Override
    public DataResult<List<PersonResponse>> findByNameContainingIgnoreCase(String name) {
        List<Person> personList = this.personDao.findByNameContainingIgnoreCase(name);

        List<PersonResponse> personResponseList = mapPersonToPersonResponseList(personList);

        return new SuccessDataResult<>(personResponseList, "Successfully retrieved persons.");
    }

    @Override
    public DataResult<List<PersonResponse>> findByHasDrivingLicense(Boolean drivingLicense) {
        List<Person> personList = this.personDao.findByHasDrivingLicense(drivingLicense);

        List<PersonResponse> personResponseList = mapPersonToPersonResponseList(personList);

        return new SuccessDataResult<>(personResponseList, "Successfully retrieved persons.");
    }

    @Override
    public DataResult<List<PersonResponse>> getChildCount(Integer count) {
        List<Person> personList = this.personDao.getPersonListByChildCount(count);

        List<PersonResponse> personResponseList = personList.stream()
                .map(this::mapPersonToPersonResponse)
                .collect(Collectors.toList());

        return new SuccessDataResult<>(personResponseList, "Successfully retrieved persons.");
    }

    @Override
    public DataResult<Result> add(Person person) {
        this.personDao.save(person);
        return new SuccessDataResult<>("Citizen added");
    }

    @Override
    public DataResult<PersonResponse> findByID(Integer id) {
        Person person = this.personDao.findByID(id);
        PersonResponse personResponse = mapPersonToPersonResponse(person);
        return new SuccessDataResult<>(personResponse, "Successfully retrieved person.");
    }


    private List<ChildrenResponse> getChildrenByID(Integer id) {
        return this.personDao.getChildrenByID(id);
    }

    private PersonResponse mapPersonToPersonResponse(Person person) {
        PersonResponse personResponse = new PersonResponse();
        personResponse.setId(person.getID());
        personResponse.setName(person.getName() + " " +person.getLastName());
        personResponse.setIsCitizen(person.isCitizen());
        personResponse.setHasDrivingLicense(person.isHasDrivingLicense());
        List<ChildrenResponse> childrenResponses = getChildrenByID(person.getID());
        personResponse.setChildren(childrenResponses);

        return personResponse;
    }
    private List<PersonResponse> mapPersonToPersonResponseList(List<Person> personList) {

        List<PersonResponse> personResponseList = new ArrayList<>();
        for(Person person : personList){

            PersonResponse personResponse = mapPersonToPersonResponse(person);
            personResponseList.add(personResponse);

        }
        return personResponseList;
    }



}
