package com.citizen.domain.concretes;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Person", schema = "public")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "isCitizen")
    private boolean isCitizen;

    @Column(name = "hasDrivingLicense")
    private boolean hasDrivingLicense;

    @JsonManagedReference
    @OneToMany(mappedBy = "childPerson", fetch = FetchType.LAZY)
    private List<Relative> parentRelatives;

    @JsonManagedReference
    @OneToMany(mappedBy = "parentPerson", fetch = FetchType.LAZY)
    private List<Relative> childRelatives;

}
