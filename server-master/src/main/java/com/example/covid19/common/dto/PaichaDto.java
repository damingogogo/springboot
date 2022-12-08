package com.example.covid19.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PaichaDto {
    private int id;
    private String lxb_Gao;
    private String lxb_Jing;
    private String lxb_MiJie;
    private String lxb_MJGao;
    private String lxb_JuJi;
    private int count;
    private String color;
    @NotBlank
    private String base64;
    @NotBlank
    private String base65;
}
