package com.citizen.service.abstracts;

import com.citizen.domain.concretes.Person;
import com.citizen.repository.abstracts.PersonDao;
import com.citizen.results.DataResult;
import com.citizen.results.Result;
import com.citizen.service.concretes.ChildrenResponse;
import com.citizen.service.concretes.PersonResponse;

import java.util.List;

public interface PersonService {
    DataResult<List<PersonResponse>> getAll();
    DataResult<List<PersonResponse>> findByIsCitizen(Boolean isCitizen);
    DataResult<List<PersonResponse>> findByNameContainingIgnoreCase(String name);
    DataResult<List<PersonResponse>> findByHasDrivingLicense(Boolean drivingLicense);

    DataResult<List<PersonResponse>> getChildCount(Integer count);

    DataResult<Result> add(Person person);
    DataResult<PersonResponse> findByID(Integer id);

}
