package com.example.covid19.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("grade")
@ApiModel(value="Grade对象", description="")
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "年级名称")
    private String name;

    @ApiModelProperty(value = "年级主任")
    private String master;

    @ApiModelProperty(value = "年级总人数")
    private String number;

    @ApiModelProperty(value = "班级总人数")
    private String classNumber;

    @ApiModelProperty(value = "描述")
    private String detail;

    @ApiModelProperty(value = "grade_school")
    private Integer sid;

}
