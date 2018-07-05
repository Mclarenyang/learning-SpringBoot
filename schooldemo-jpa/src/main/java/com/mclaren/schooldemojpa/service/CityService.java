package com.mclaren.schooldemojpa.service;

import com.mclaren.schooldemojpa.domain.City;
import com.mclaren.schooldemojpa.tool.Params;
import org.springframework.data.domain.Page;

import java.util.List;


public interface CityService {

    City addCity(Params params);

    Page<City> findCity(Params params);

    Page<City> findAllCity(Params params);

    public List<City> findByCityName(Params params);

}
