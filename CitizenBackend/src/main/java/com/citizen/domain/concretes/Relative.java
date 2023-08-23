package com.citizen.domain.concretes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "relative", schema = "public")
public class Relative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relativeid")
    private int relativeid;

    /*@Column(name = "ParentID")
    private int parentID;

    @Column(name = "ChildID")
    private int childID;*/

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "ParentID")
    private Person parentPerson;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "ChildID")
    private Person childPerson;

}
