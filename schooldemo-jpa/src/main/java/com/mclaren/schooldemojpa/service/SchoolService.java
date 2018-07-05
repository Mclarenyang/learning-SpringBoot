package com.mclaren.schooldemojpa.service;

import com.mclaren.schooldemojpa.domain.School;
import com.mclaren.schooldemojpa.tool.Params;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public interface SchoolService {

    School addSchool(Params params);

    boolean deleteSchool(Integer id);

    Page<School> findSchool(Params params);

    School editSchool(Params params);
}
