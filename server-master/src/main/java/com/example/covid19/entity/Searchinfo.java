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
@TableName("searchinfo")
@ApiModel(value="Searchinfo对象", description="")
public class Searchinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String xingming;

    private String area;

    private String date;


}
