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
 * @since 2022-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("school")
@ApiModel(value="School对象", description="")
public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "学校名称")
    private String name;

    @ApiModelProperty(value = "校长")
    private String master;

    @ApiModelProperty(value = "学校地址")
    private String address;

    @ApiModelProperty(value = "学校总人数")
    private Integer numberStudent;

    @ApiModelProperty(value = "学校年级数量")
    private Integer numberGrade;

    @ApiModelProperty(value = "学校班级数量")
    private Integer numberClass;

    @ApiModelProperty(value = "学校描述")
    private String detail;


}
