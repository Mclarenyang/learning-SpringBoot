package com.mclaren.hellosb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class School {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String city;
    private Double scoreline;

    public School(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getScoreline() {
        return scoreline;
    }

    public void setScoreline(Double scoreline) {
        this.scoreline = scoreline;
    }


}
