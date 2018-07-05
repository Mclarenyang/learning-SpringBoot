package com.mclaren.schooldemojpa.service.impl;

import com.mclaren.schooldemojpa.dao.SchoolRepository;
import com.mclaren.schooldemojpa.domain.City;
import com.mclaren.schooldemojpa.domain.School;
import com.mclaren.schooldemojpa.service.CityService;
import com.mclaren.schooldemojpa.service.SchoolService;
import com.mclaren.schooldemojpa.tool.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private CityService cityService;

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public School addSchool(Params params) {

        School newSchool = new School();
        newSchool.setSchoolName(params.getSchoolName());
        newSchool.setScoreline(params.getScoreline());

        //为新增学校刷新一个城市
        newSchool.setCity(cityService.addCity(params));

        return schoolRepository.save(newSchool);
    }

    @Override
    public boolean deleteSchool(Integer id) {
        Params params = new Params();
        params.setSchoolId(id);
        //查询_如果存在
        Integer searchSchoolNum = findSchool(params).getSize();
        if(searchSchoolNum != 0){
            schoolRepository.delete(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Page<School> findSchool(Params params) {

        Integer pageNum = 0;
        Integer pageSize = 1;

        if(params.getPageNum() != null && params.getPageSize() != null) {
            pageNum = params.getPageNum();
            pageSize = params.getPageSize();
        };

        //使用分数降序（直接new Sort / 如果排序条件较多可以使用 Sort.Order 结合 List<sortProperties>实现）
        Sort sort = new Sort(Sort.Direction.DESC,("scoreline"));

        Pageable pageable = new PageRequest(pageNum,pageSize,sort);

        return schoolRepository.findAll(new Specification<School>() {
            List<Predicate> preList = new ArrayList<>();
            @Override
            public Predicate toPredicate(Root<School> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                //学校Id查找
                if(params.getSchoolId() != null){

                    Predicate predicate;
                    predicate = criteriaBuilder.equal(root.get("schoolId").as(String.class), params.getSchoolId());
                    preList.add(predicate);

                }
                //学校名称模糊查询
                if(params.getSchoolName() != null && !params.getSchoolName().equals("")){

                    Predicate predicate;
                    predicate = criteriaBuilder.like(root.get("schoolName").as(String.class), ("%" + params.getSchoolName() + "%"));
                    preList.add(predicate);

                }

                //分数线查询 <=?
                if(params.getScoreline() != null){

                    Predicate predicate;
                    predicate = criteriaBuilder.lessThanOrEqualTo(root.get("scoreline").as(Double.class), Double.valueOf(params.getScoreline()));
                    preList.add(predicate);
                }

                //城市名称连接查询
                if(params.getCityName() != null && !params.getCityName().equals("")){


                    Join<City, School> join = root.join(("city"), JoinType.LEFT);
                    Predicate predicate;
                    predicate = criteriaBuilder.equal(join.get("cityName").as(String.class), params.getCityName());
                    preList.add(predicate);

                }

                //城市特点连接模糊查询
                if(params.getChara() != null && !params.getChara().equals("")){

                    Join<City, School> join = root.join(("city"), JoinType.LEFT);
                    Predicate predicate;
                    predicate = criteriaBuilder.like(join.get("chara").as(String.class), ("%" + params.getChara() + "%"));
                    preList.add(predicate);
                }


                Predicate[] pre = new Predicate[preList.size()];
                criteriaQuery.where(preList.toArray(pre));
                return criteriaBuilder.and(preList.toArray(pre));

            }
        },pageable);
    }

    //修改学校信息
    public School editSchool(Params params){

        School editSchool = new School();
        editSchool.setSchoolId(params.getSchoolId());
        editSchool.setSchoolName(params.getSchoolName());
        editSchool.setScoreline(params.getScoreline());
        editSchool.setCity(cityService.addCity(params));

        return schoolRepository.save(editSchool);

    }
}
