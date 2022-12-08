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
 * 
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("vote")
@ApiModel(value="Vote对象", description="")
public class Vote implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "投票标题")
    private String title;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "图片地址")
    private String imgUrl;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "1匿名，0不匿名")
    private String annoymous;

    @ApiModelProperty(value = "被投数量")
    private String num;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
