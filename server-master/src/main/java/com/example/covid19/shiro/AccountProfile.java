package com.example.covid19.shiro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AccountProfile implements Serializable {

    private Integer id;

    private String name;

    private String password;

    @ApiModelProperty(value = "出生日期")
    private Date day;
}
