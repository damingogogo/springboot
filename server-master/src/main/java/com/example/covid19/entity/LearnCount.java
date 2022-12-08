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
 * @author BaiZhengChun
 * @since 2022-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("learn_count")
@ApiModel(value="LearnCount对象")
public class LearnCount implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer count;

    private Integer id;


}
