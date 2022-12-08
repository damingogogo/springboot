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
 * @since 2022-03-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("apiinfo")
@ApiModel(value="Apiinfo对象", description="")
public class Apiinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String lineContext;

    private String lineNo;


}
