package com.citizen.repository.abstracts;


import com.citizen.domain.concretes.Person;
import com.citizen.service.concretes.ChildrenResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PersonDao extends JpaRepository <Person, Integer> {

    List<Person> findByIsCitizen(Boolean isCitizen);

    List<Person> findByNameContainingIgnoreCase(String name);

    List<Person> findByHasDrivingLicense(Boolean drivingLicense);

    @Query(value = "SELECT * FROM PERSON WHERE PERSON.ID IN (" +
            "SELECT parentid FROM PERSON P " +
            "JOIN relative r ON P.id = r.parentid " +
            "Group by parentid " +
            "Having Count(parentid) = :count )", nativeQuery = true)
    List<Person> getPersonListByChildCount(Integer count);

    /*@Query("SELECT p FROM Person p " +
            "JOIN p.relatives r " +
            "GROUP BY r.parentId " +
            "HAVING COUNT(r.parentId) = :count")
    List<Person> getPersonListByChildCount(Integer count);*/

    Person findByID(Integer id);

    /*@Query(value = "SELECT new com.citizen.service.concretes.ChildrenResponse (p.name," +
            "p.lastName," +
            "p.id) " +
            "from Person p " +
            "WHERE p.id IN (Select r.childPerson from " +
            "Relative r " +
            "Where r.parentPerson = :id)")
    List<ChildrenResponse> getChildrenByID(Integer id);*/

    @Query(value = "SELECT new com.citizen.service.concretes.ChildrenResponse(p.name ||' '|| p.lastName AS name, p.id AS id) " +
            "FROM Person p " +
            "WHERE p.id IN (SELECT r.childPerson.id FROM Relative r WHERE r.parentPerson.id = :id)")
    List<ChildrenResponse> getChildrenByID(Integer id);
}
