package com.example.covid19.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("school_grade")
@ApiModel(value="SchoolGrade对象", description="VIEW")
public class SchoolGrade implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学校名称")
    private String name1;

    @ApiModelProperty(value = "校长")
    private String master1;

    @ApiModelProperty(value = "学校地址")
    private String address;

    @ApiModelProperty(value = "学校总人数")
    private String numberStudent;

    @ApiModelProperty(value = "学校年级数量")
    private String numberGrade;

    @ApiModelProperty(value = "学校班级数量")
    private String numberClass;

    @ApiModelProperty(value = "学校描述")
    private String detail1;

    @ApiModelProperty(value = "年级名称")
    private String name;

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "年级主任")
    private String master;

    @ApiModelProperty(value = "年级总人数")
    private String number;

    @ApiModelProperty(value = "描述")
    private String detail;

    @ApiModelProperty(value = "学校外键")
    private Integer sid;


}
