package com.example.covid19.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("teacher")
@ApiModel(value="Teacher对象", description="")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "工号")
    private String jobNumber;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "年龄")
    private String age;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "是否已婚")
    private String marry;

    @ApiModelProperty(value = "是否接种疫苗")
    private String vaccine;

    @ApiModelProperty(value = "家庭住址")
    private String address;

    @ApiModelProperty(value = "爱好")
    private String hobby;


}
