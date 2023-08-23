package com.citizen.service.mapper;

import com.citizen.domain.concretes.Person;
import com.citizen.service.concretes.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonResponseMapper {
    PersonResponseMapper instance = Mappers.getMapper(PersonResponseMapper.class);

    @Named("convertEntityToResponse")
    PersonResponse convertEntityToResponse(Person person);

    @Named("convertEntityListToResponseList")
    List<PersonResponse> convertEntityListToResponseList(List<Person> personList);

}
