package com.mclaren.schooldemojpa.dao;

import com.mclaren.schooldemojpa.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer>, JpaSpecificationExecutor<City> {

    List<City> findByCityName(String cityName);

}
