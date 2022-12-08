package com.example.covid19.shiro;

import com.example.covid19.entity.LoginUserInfo;
import com.example.covid19.service.LoginUserInfoService;
import com.example.covid19.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {

    final JwtUtils jwtUtils;

    final LoginUserInfoService loginUserInfoService;

    public AccountRealm(JwtUtils jwtUtils, LoginUserInfoService loginUserInfoService) {
        this.jwtUtils = jwtUtils;
        this.loginUserInfoService = loginUserInfoService;
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;

        String uid = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        LoginUserInfo loginUserInfo = loginUserInfoService.getById(Long.valueOf(uid));
        if (loginUserInfo == null) {
            throw new UnknownAccountException("Account is not exist");
        }
        AccountProfile profile = new AccountProfile();
        BeanUtils.copyProperties(loginUserInfo, profile);

        return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
    }
}