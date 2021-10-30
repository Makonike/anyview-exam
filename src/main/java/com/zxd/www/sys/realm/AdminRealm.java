package com.zxd.www.sys.realm;

import com.alibaba.fastjson.JSON;
import com.zxd.www.global.constant.CommonConstant;
import com.zxd.www.global.constant.RedisConstant;
import com.zxd.www.global.util.RedisUtil;
import com.zxd.www.sys.entity.AdminToken;
import com.zxd.www.sys.entity.SysAdminEntity;
import com.zxd.www.sys.service.ShiroService;
import com.zxd.www.sys.util.AdminJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * @author Makonike
 * @date 2021-10-14 19:34
 **/
@Component
@Slf4j
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private ShiroService shiroService;

    @Autowired
    @Lazy
    private RedisUtil redisUtil;

    @Override
    public Class<AdminToken> getAuthenticationTokenClass() {
        return AdminToken.class;
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
     * 授权
     * @param principals 身份
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("=== 权限认证 ===");
        SysAdminEntity adminEntity = null;
        Integer adminId = null;
        if(principals != null){
            adminEntity = (SysAdminEntity) principals.getPrimaryPrincipal();
            adminId = adminEntity.getAdminId();
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 设置用户拥有的权限集合, example: "admin,teacher"
        Set<String> roleSet = shiroService.getRoles(adminId);
        info.setRoles(roleSet);

        // 设置用户拥有的权限集合, example: "sys:user:list,sys:admin:add"
        Set<String> permissionSet = shiroService.getPerms(adminId);
        info.setStringPermissions(permissionSet);

        return info;
    }

    /**
     * 身份认证
     * @param token token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String tokenString = (String) token.getCredentials();
        if(!StringUtils.hasText(tokenString)){
            log.info("=== 身份认证失败 ===");
            throw new AuthenticationException("token为空");
        }
        // 验证token有效性
        SysAdminEntity loginUser = checkToken(tokenString);

        return new SimpleAuthenticationInfo(loginUser, tokenString, getName());
    }

    /**
     * 校验token
     * @param token token
     */
    private SysAdminEntity checkToken(String token){

        Integer adminId = AdminJwtUtil.getAdminId(token);
        if(adminId == null){
            throw new AuthenticationException("token非法/无效!");
        }

        SysAdminEntity loginUser = new SysAdminEntity();
        String entityJson = (String) redisUtil.get(RedisConstant.PREFIX_ADMIN_INFO + adminId);
        SysAdminEntity adminEntity = JSON.parseObject(entityJson, SysAdminEntity.class);

        if(adminEntity == null){
            throw new AuthenticationException("用户不存在!");
        }

        BeanUtils.copyProperties(adminEntity, loginUser);
        return loginUser;
    }

}
