package com.example.covid19.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
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
 * @since 2022-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("grade_class")
@ApiModel(value="GradeClass对象", description="VIEW")
public class GradeClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Integer id1;

    @ApiModelProperty(value = "年级名称")
    private String name1;

    @ApiModelProperty(value = "年级主任")
    private String master1;

    @ApiModelProperty(value = "年级总人数")
    private String number1;

    @ApiModelProperty(value = "描述")
    private String detail1;

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "班级名称")
    private String name;

    @ApiModelProperty(value = "班级班主任")
    private String master;

    @ApiModelProperty(value = "班级总人数")
    private String number;

    @ApiModelProperty(value = "描述")
    private String detail;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "grade_class")
    private Integer gid;


}
