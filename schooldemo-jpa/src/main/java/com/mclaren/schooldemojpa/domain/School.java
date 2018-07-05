package com.mclaren.schooldemojpa.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class School {

    @Id
    @GeneratedValue
    @Column(name = "schoolId")
    private Integer schoolId;
    private String schoolName;
    @ManyToOne
    private City city;
    private Double scoreline;

}
