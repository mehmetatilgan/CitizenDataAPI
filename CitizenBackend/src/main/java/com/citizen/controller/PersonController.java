package com.citizen.controller;

import com.citizen.domain.concretes.Person;
import com.citizen.results.DataResult;
import com.citizen.results.Result;
import com.citizen.service.abstracts.PersonService;
import com.citizen.service.concretes.PersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@Repository
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("getall")
    public DataResult<List<PersonResponse>> getAll(){
        return this.personService.getAll();
    }

    @GetMapping("findByIsCitizen")
    @ResponseBody
    public DataResult<List<PersonResponse>> findByIsCitizen(@RequestParam("isCitizen") Boolean b){
        return this.personService.findByIsCitizen(b);
    }
    @GetMapping("getByName")
    @ResponseBody
    public DataResult<List<PersonResponse>> getByName(@RequestParam("name") String name){
        return this.personService.findByNameContainingIgnoreCase(name);
    }

    @GetMapping("getByHasLicense")
    @ResponseBody
    public DataResult<List<PersonResponse>> findByHasDrivingLicense(@RequestParam("license") Boolean drivingLicense){
        return this.personService.findByHasDrivingLicense(drivingLicense);
    }

    @GetMapping("getPersonListByChildCount")
    @ResponseBody
    public DataResult<List<PersonResponse>> getPersonListByChildCount(@RequestParam("count") Integer count) {
        return this.personService.getChildCount(count);
    }

    @GetMapping("findByID")
    @ResponseBody
    public ResponseEntity<PersonResponse> findByID(@RequestParam("id")Integer id){
        PersonResponse person = this.personService.findByID(id).getData();
        if (person == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("addPerson")
    public Result add(Person person) {
        return this.personService.add(person);
    }
}
