package com.example.covid19.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2022-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("paicha")
@ApiModel(value="Paicha对象", description="")
public class Paicha implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @TableField("lxb_Gao")
    private String lxbGao;

    @TableField("lxb_Jing")
    private String lxbJing;

    @TableField("lxb_MJGao")
    private String lxbMjgao;

    @TableField("lxb_MiJie")
    private String lxbMijie;

    @TableField("lxb_JuJi")
    private String lxbJuji;

    private Integer count;

    private String color;


}
