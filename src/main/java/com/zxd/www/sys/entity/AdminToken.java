package com.zxd.www.sys.entity;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 管理员token
 * @author Makonike
 * @date 2021-10-14 16:39
 **/
public class AdminToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    private final String token;

    public AdminToken(String token) {
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
