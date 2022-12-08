package com.example.covid19.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.covid19.common.dto.ImageUploadDto;
import com.example.covid19.common.dto.LoginDto;
import com.example.covid19.common.dto.RegisterDto;
import com.example.covid19.common.dto.TokenDto;
import com.example.covid19.entity.LoginUserInfo;
import com.example.covid19.common.lang.Result;
import com.example.covid19.service.LoginUserInfoService;
import com.example.covid19.util.JwtUtils;
import com.mysql.cj.util.Base64Decoder;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.*;
import javax.net.ssl.SSLException;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/account")
public class AccountController {

    final LoginUserInfoService loginUserInfoService;
    static LoginUserInfoService loginUserInfoServiceStatic;

    final JwtUtils jwtUtils;
    static JwtUtils jwtUtilsStatic;

    final String secret;

    final String header;

    public AccountController(LoginUserInfoService loginUserInfoService, JwtUtils jwtUtils) {
        this.loginUserInfoService = loginUserInfoService;
        loginUserInfoServiceStatic = loginUserInfoService;
        this.jwtUtils = jwtUtils;
        jwtUtilsStatic = jwtUtils;
        this.secret = jwtUtils.getSecret();
        this.header = jwtUtils.getHeader();
    }

    /**
     * Validated验证数据是否合法，RequestBody请求体
     * @param loginDto
     * @param response
     * @return
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto,
                        HttpServletResponse response) {
        LoginUserInfo user = loginUserInfoService.getOne(new QueryWrapper<LoginUserInfo>().eq(
                "name",
                loginDto.getName()
        ));
        if (user == null) {
            return Result.fail(406, "用户不存在", null);
        }
        boolean flag = user.getPassword().equals(
                SecureUtil.hmacSha1(secret)
                        .digestHex(loginDto.getPassword())
        );
        if (!flag) {
            return Result.fail(406, "密码不正确", null);
        }
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader(header, jwt);
        response.setHeader("Access-Control-Expose-Headers", header);
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getName())
                .map());
    }

    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterDto registerDto) {
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setName(registerDto.getName());
        loginUserInfo.setPassword(SecureUtil.hmacSha1(secret)
                .digestHex(registerDto.getPassword()));
       loginUserInfo.setPhone(registerDto.getPhone());
        LoginUserInfo user = loginUserInfoService.getOne(new QueryWrapper<LoginUserInfo>().eq(
                "name",
                registerDto.getName()
        ));
        if (user != null) {
            return Result.fail(406, "用户已经存在", null);
        }
        boolean flag = loginUserInfoService.save(loginUserInfo);
        return flag ? Result.success(null) : Result.fail(406, "用户创建失败", null);
    }

    @PostMapping("/checkToken")
    public Result checkToken(@RequestBody TokenDto tokenDto, HttpServletResponse response) {
        String token = tokenDto.getToken();
        System.out.println(token);
        // Claims解析token
        Claims claims = jwtUtils.getClaimByToken(token);
        if (claims == null || jwtUtils.isTokenExpired(claims.getExpiration())) {
            return Result.fail(498, "Token已经过期", null);
        }
        return Result.success(null);
    }
    @RequiresAuthentication
    @PostMapping("/phone")
    public Result phone(@RequestBody TokenDto tokenDto){
        String token = tokenDto.getToken();
        System.out.println(token);
        Claims claims = jwtUtils.getClaimByToken(token);
        if (claims == null || jwtUtils.isTokenExpired(claims.getExpiration())) {
            return Result.fail(444, "Token过期", null);
        }
        String id = claims.getSubject();
        LoginUserInfo loginUserInfo =  loginUserInfoService.getById(id);
        System.out.println(loginUserInfo.getPhone());
        return Result.success(loginUserInfo);
    }
}

