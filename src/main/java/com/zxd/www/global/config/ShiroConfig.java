package com.zxd.www.global.config;

import com.zxd.www.user.filter.UserFilter;
import com.zxd.www.user.realm.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 * @author Makonike
 * @date 2021-10-13 0:01
 **/
@Configuration
public class ShiroConfig {

    /**
     * Filter Chain定义说明
     * <p>
     * 1、一个URL可以配置多个Filter，使用逗号分隔 <br/>
     * 2、当设置多个过滤器时，全部验证通过，才视为通过 <br/>
     * 3、部分过滤器可指定参数，如perms，roles <br/>
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 排除登录接口
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/info", "userFilter");

        // 学生接口
        filterChainDefinitionMap.put("/student/**", "userFilter");

        // 添加自己的过滤器并取名
        Map<String, Filter> filterMap = new HashMap<>(2);
        filterMap.put("userFilter", new UserFilter());
        // 设置过滤器
        shiroFilterFactoryBean.setFilters(filterMap);

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 设置realm
        securityManager.setRealm(userRealm);

//        // TODO: 关闭shiro自带的session
//        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
//        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
//        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
//        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
//        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }



}
