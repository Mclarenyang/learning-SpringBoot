package com.mclaren.schooldemojpa.tool;

import lombok.Data;

@Data
public class Params {

    private Integer schoolId;
    private String schoolName;
    private Double scoreline;

    private String cityName;
    private String chara;

    private Integer pageNum;
    private Integer pageSize;

}
