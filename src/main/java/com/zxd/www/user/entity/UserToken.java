package com.zxd.www.user.entity;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * UserToken实体类
 * @author Makonike
 * @date 2021-10-12 14:47
 **/
public class UserToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    private final String token;

    public UserToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
