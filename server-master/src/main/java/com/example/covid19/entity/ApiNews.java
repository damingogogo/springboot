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
 * @author BaiZhengChun
 * @since 2022-03-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("api_news")
@ApiModel(value="ApiNews对象", description="")
public class ApiNews implements Serializable {

    private static final long serialVersionUID = 1L;

    private String summary;

    private String title;

    private LocalDateTime pubdate;

    private String infosource;

    private String sourceurl;

    private Integer id;

    private LocalDateTime pubdatestr;


}
