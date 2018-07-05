package com.mclaren.schooldemojpa.service.impl;

import com.mclaren.schooldemojpa.dao.CityRepository;
import com.mclaren.schooldemojpa.domain.City;
import com.mclaren.schooldemojpa.service.CityService;
import com.mclaren.schooldemojpa.tool.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class CityServiceImpl implements CityService {


    @Autowired
    private CityRepository cityRepository;


    @Override
    public City addCity(Params params) {


        //先按照cityName查,如果存在则绑定
        if(findByCityName(params).size() != 0) {

            return findByCityName(params).get(0);

        }

        City newCity = new City();
        newCity.setCityName(params.getCityName());

        if(params.getChara() != null && params.getChara() != "") {
            newCity.setChara(params.getChara());
        }else {
            newCity.setChara("");
        }
        return cityRepository.save(newCity);
    }


    @Override
    public Page<City> findCity(Params params) {

        Integer pageNum = 0;
        Integer pageSize = 1;

        if(params.getPageNum() != null && params.getPageSize() != null) {
            pageNum = params.getPageNum();
            pageSize = params.getPageSize();
        };

        Pageable pageable = new PageRequest(pageNum,pageSize);

        //条件查找todo 按照名称
        return cityRepository.findAll(new Specification<City>(){

            @Override
            public Predicate toPredicate(Root<City> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> preList = new ArrayList<>();
                //名字字段匹配查询
                if(params.getCityName() != null && !params.getCityName().equals("")){

                    Predicate predicate;
                    predicate = criteriaBuilder.equal(root.get("cityName").as(String.class), params.getCityName());
                    preList.add(predicate);

                }

                //特点字段模糊查询
                if(params.getChara() != null && !params.getChara().equals("")){

                    Predicate predicate;
                    predicate = criteriaBuilder.like(root.get("chara").as(String.class),("%" + params.getChara() + "%"));
                    preList.add(predicate);
                }


                Predicate[] pre = new Predicate[preList.size()];
                criteriaQuery.where(preList.toArray(pre));
                return criteriaBuilder.and(preList.toArray(pre));
            }
        },pageable);

    }

    public Page<City> findAllCity(Params params){

        Integer pageNum = params.getPageNum();
        Integer pageSize = params.getPageSize();

        Pageable pageable = new PageRequest(pageNum,pageSize);
        return cityRepository.findAll(pageable);
    }


    //按照名字查询城市
    @Override
    public List<City> findByCityName(Params params) {

        return cityRepository.findByCityName(params.getCityName());

    }

    //修改城市func
    public City editCity(Params params){

        City editCity = new City();
        editCity.setCityId(params.getSchoolId());
        editCity.setChara(params.getChara());
        editCity.setCityName(params.getCityName());
        return  cityRepository.save(editCity);
    }

}

