package com.mclaren.hellosb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SayHelloController {

    @GetMapping(value = "/hello")
    public String Say(){
        return "hello Spring Boot";
    }

    @Autowired
    private SchoolRepository schoolRepository;

    //返回所有学校
    @GetMapping(value = "/allSchool")
    public List<School> getAllSchool(){
        return schoolRepository.findAll();
    }

    //添加一个学校
    @PostMapping(value = "/addSchool")
    public School addSchool(@RequestParam("name") String name,
                            @RequestParam("city") String city,
                            @RequestParam("scoreline") Double scoreline){
        School newSchool = new School();
        newSchool.setCity(city);
        newSchool.setName(name);
        newSchool.setScoreline(scoreline);

        return schoolRepository.save(newSchool);

    }

    //删除一个学校
    @PostMapping(value = "/deleteSchool/{id}")
    public void deleteSchool(@PathVariable("id") Integer id){

        schoolRepository.delete(id);

    }

    //按照id修改学校的属性
    @PostMapping(value = "editSchool")
    public School editSchool(@RequestParam("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("city") String city,
                             @RequestParam("scoreline") Double scoreline){

        School editedSchool = new School();

        editedSchool.setId(id);
        editedSchool.setName(name);
        editedSchool.setCity(city);
        editedSchool.setScoreline(scoreline);

        return schoolRepository.save(editedSchool);
    }
//
//    //按id查找学校 //url显式查找
//    @PostMapping(value = "/findSchoolById/{id}")
//    public School findSchool(@PathVariable("id") Integer id){
//
//        return schoolRepository.findOne(id);
//
//    }

    //按id查找学校 //post隐式查找
    @PostMapping(value = "/findSchoolById")
    public School findSchool(@RequestParam("id") Integer id){

        return schoolRepository.findOne(id);

    }

    //按照名字查找学校
//    @PostMapping(value = "/findSchoolByName")
//    public School findSchoolByName(@RequestParam("name") String name){
//
//        School onlyNameSchool = new School();
//        onlyNameSchool.setName(name);
//        return schoolRepository.findOne(onlyNameSchool);
//    }
}
