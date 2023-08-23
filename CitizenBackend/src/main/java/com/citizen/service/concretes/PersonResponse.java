package com.citizen.service.concretes;

import lombok.Data;

import java.util.List;

@Data
public class PersonResponse {
    private int id;
    private String name;
    private Boolean isCitizen;
    private Boolean hasDrivingLicense;
    private List<ChildrenResponse> children;
}
