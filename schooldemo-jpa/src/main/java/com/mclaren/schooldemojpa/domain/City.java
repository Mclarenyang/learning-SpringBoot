package com.mclaren.schooldemojpa.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
public class City {

    @Id
    @GeneratedValue
    @Column(name = "cityId")
    private Integer cityId;
    private String cityName;
    private String chara;

}
