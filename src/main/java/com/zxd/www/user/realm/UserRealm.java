package com.zxd.www.user.realm;

import com.alibaba.fastjson.JSON;
import com.zxd.www.global.constant.RedisConstant;
import com.zxd.www.global.util.JwtUtil;
import com.zxd.www.global.util.RedisUtil;
import com.zxd.www.user.entity.UserEntity;
import com.zxd.www.user.entity.UserToken;
import com.zxd.www.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 用户登录鉴权和获取用户授权
 * @author Makonike
 * @date 2021-10-12 14:50
 **/
@Component
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Class<UserToken> getAuthenticationTokenClass() {
        return UserToken.class;
    }

    /**
     * 获取用户权限信息，只有当触发检测用户权限的方法时才调用此方法
     * @param principalCollection token
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 考虑到用户的角色比较单一，暂时不写用户的权限认证
        return new SimpleAuthorizationInfo();
    }

    /**
     * 每一个Ream都有一个supports方法，用于检测是否支持此Token
     * 必须重写此方法，不然Shiro会报错.
     * 参考 https://blog.csdn.net/juxua_xatu/article/details/80668836
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && getAuthenticationTokenClass().isAssignableFrom(token.getClass());
    }

    /**
     * 身份认证，验证账号密码是否正确，获取身份验证信息，错误则抛出异常
     * @param authenticationToken user-token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     * @throws AuthenticationException 认证失败异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        if(!StringUtils.hasText(token)){
            log.info("===身份认证失败===");
            throw new IncorrectCredentialsException("token为空!");
        }
        // 校验token有效性
        UserEntity loginUser = this.checkUserTokenIsEffect(token);

        return new SimpleAuthenticationInfo(loginUser, token, getName());
    }

    /**
     * 校验token有效性
     * @param token token
     */
    private UserEntity checkUserTokenIsEffect(String token){
        Integer userId = JwtUtil.getUserId(token);
        if(userId == null){
            throw new AuthenticationException("token非法/无效");
        }

        // 查询用户信息
        UserEntity loginUser = new UserEntity();
        // 从redis缓存中获取用户信息
        String entityJson = (String) redisUtil.get(RedisConstant.PREFIX_USER_INFO + userId);
        UserEntity userEntity = JSON.parseObject(entityJson, UserEntity.class);
        if(userEntity == null){
            throw new AuthenticationException("用户不存在!");
        }

        BeanUtils.copyProperties(userEntity, loginUser);
        return loginUser;
    }

}
