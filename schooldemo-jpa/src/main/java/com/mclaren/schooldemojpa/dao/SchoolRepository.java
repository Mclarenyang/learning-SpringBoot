package com.mclaren.schooldemojpa.dao;

import com.mclaren.schooldemojpa.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface SchoolRepository extends JpaRepository<School, Integer>, JpaSpecificationExecutor<School> {

}
