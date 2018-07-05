package com.mclaren.schooldemojpa.controller;

import com.mclaren.schooldemojpa.domain.City;
import com.mclaren.schooldemojpa.domain.School;
import com.mclaren.schooldemojpa.service.CityService;
import com.mclaren.schooldemojpa.service.SchoolService;
import com.mclaren.schooldemojpa.tool.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class SpringDemoRestController {

    @Autowired
    private SchoolService schoolService;
    @Autowired
    private CityService cityService;

    //增加学校
    @PostMapping(value = "/addSchool")
    public School addSchool(@RequestParam(value = "schoolName") String schoolName,
                            @RequestParam(value = "cityName") String cityName,
                            @RequestParam(value = "scoreline") Double scoreline){

        Params params = new Params();
        params.setSchoolName(schoolName);
        params.setCityName(cityName);
        params.setScoreline(scoreline);

        return schoolService.addSchool(params);

    }


    //增加城市
    @PostMapping(value = "/addCity")
    public City addCity(@RequestParam(value = "cityName") String cityName,
                        @RequestParam(value = "chara") String chara){

        Params params = new Params();
        params.setCityName(cityName);
        params.setChara(chara);
        return cityService.addCity(params);

    }

    //删除学校
    @PostMapping(value = "/deleteSchool")
    public boolean deleteSchool(@RequestParam(value = "schoolId") Integer schoolId){
        return schoolService.deleteSchool(schoolId);
    }

    //修改学校 按照Id
    @PostMapping(value = "editSchool")
    public School editSchool(@RequestParam(value = "schoolId") Integer schoolId,
                             @RequestParam(value = "schoolName") String schoolName,
                             @RequestParam(value = "cityName") String cityName,
                             @RequestParam(value = "scoreline") Double scoreline){

        Params params = new Params();
        params.setSchoolId(schoolId);
        params.setSchoolName(schoolName);
        params.setCityName(cityName);
        params.setScoreline(scoreline);

        return schoolService.editSchool(params);
    }



    //多表多条件查询学校
    @PostMapping(value = "/findSchool")
    public Page<School> findSchool(@RequestParam(value = "schoolId") Integer schoolId,
                                   @RequestParam(value = "schoolName") String schoolName,
                                   @RequestParam(value = "cityName") String cityName,
                                   @RequestParam(value = "chara") String chara,
                                   @RequestParam(value = "scoreline") Double scoreline,
                                   @RequestParam(value = "pageNum") Integer pageNum,
                                   @RequestParam(value = "pageSize") Integer pageSize){

        Params params = new Params();
        params.setSchoolId(schoolId);
        params.setSchoolName(schoolName);
        params.setCityName(cityName);
        params.setChara(chara);
        params.setScoreline(scoreline);
        params.setPageNum(pageNum);
        params.setPageSize(pageSize);

        return schoolService.findSchool(params);
    }

    //多条件查询城市
    @PostMapping(value = "/findCity")
    public Page<City> findCity(@RequestParam(value = "cityName") String cityName,
                               @RequestParam(value = "chara") String chara,
                               @RequestParam(value = "pageNum") Integer pageNum,
                               @RequestParam(value = "pageSize") Integer pageSize){

        Params params = new Params();
        params.setCityName(cityName);
        params.setChara(chara);
        params.setPageNum(pageNum);
        params.setPageSize(pageSize);

        return cityService.findCity(params);

    }

    //查询所有城市
    @PostMapping(value = "/findAllCity")
    public Page<City> findAllCity(@RequestParam(value = "pageNum") Integer pageNum,
                                  @RequestParam(value = "pageSize") Integer pageSize){

        Params params = new Params();
        params.setPageNum(pageNum);
        params.setPageSize(pageSize);

        return cityService.findAllCity(params);
    }

    //test func 测试findCityByCityName是否可行
    @PostMapping(value = "/findCityByCityName")
    public List<City> findCityByName(@RequestParam(value = "cityName") String cityName){

        Params params = new Params();
        params.setCityName(cityName);

        return cityService.findByCityName(params);
    }


}
