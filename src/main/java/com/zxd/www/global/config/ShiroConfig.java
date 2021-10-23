package com.zxd.www.global.config;

import com.zxd.www.sys.filter.AdminFilter;
import com.zxd.www.sys.realm.AdminRealm;
import com.zxd.www.user.filter.UserFilter;
import com.zxd.www.user.realm.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.Arrays;
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
        filterChainDefinitionMap.put("/sys/login", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");

        // 排除异常接口
        filterChainDefinitionMap.put("/user/unauthorized/**", "anon");
        filterChainDefinitionMap.put("/sys/unauthorized/**", "anon");

        // 排除ws连接接口
        filterChainDefinitionMap.put("/exam/ws", "anon");
        // 排除获取服务端时间的接口
        filterChainDefinitionMap.put("/exam/serverTime", "anon");

        // 用户接口
        filterChainDefinitionMap.put("/user/info", "userFilter");
        filterChainDefinitionMap.put("/user/update", "adminFilter");
        filterChainDefinitionMap.put("/user/save", "adminFilter");
        filterChainDefinitionMap.put("/user/id/**", "adminFilter");

        // 学生接口
        filterChainDefinitionMap.put("/student/no", "userFilter");
        filterChainDefinitionMap.put("/student/id/**", "userFilter");
        filterChainDefinitionMap.put("/student/update", "userFilter");
        filterChainDefinitionMap.put("/student/bind", "userFilter");
        filterChainDefinitionMap.put("/student/list", "adminFilter");
        filterChainDefinitionMap.put("/student/save", "adminFilter");

        // 管理员接口
        filterChainDefinitionMap.put("/sys/info", "adminFilter");

        // 教师接口
        filterChainDefinitionMap.put("/sys/teacher/update", "adminFilter");
        // 测验接口
        filterChainDefinitionMap.put("/exam/delay/**", "adminFilter");
        filterChainDefinitionMap.put("/exam/autoSave", "adminFilter");
        filterChainDefinitionMap.put("/exam/save", "adminFilter");
        filterChainDefinitionMap.put("/exam/setUp", "adminFilter");
        filterChainDefinitionMap.put("/exam/start/**", "adminFilter");
        filterChainDefinitionMap.put("/exam/stop/**", "adminFilter");
        filterChainDefinitionMap.put("/exam/update", "adminFilter");
        filterChainDefinitionMap.put("/exam/user/get", "userFilter");
        filterChainDefinitionMap.put("/exam/user/examPlan", "userFilter");

        // 测验题目表接口
        filterChainDefinitionMap.put("/exam/table/**", "adminFilter");
        // 题库接口
        filterChainDefinitionMap.put("/exam/band/**", "adminFilter");
        // 已选题目接口
        filterChainDefinitionMap.put("/exam/select/add", "adminFilter");
        filterChainDefinitionMap.put("/exam/select/delete/**", "adminFilter");
        filterChainDefinitionMap.put("/exam/select/list/**", "adminFilter");
        filterChainDefinitionMap.put("/exam/select/get/**", "userFilter");
        // 题目接口
        filterChainDefinitionMap.put("/exam/question/**", "adminFilter");
        
        // 添加自己的过滤器并取名
        Map<String, Filter> filterMap = new HashMap<>(2);
        filterMap.put("userFilter", new UserFilter());
        filterMap.put("adminFilter", new AdminFilter());
        // 设置过滤器
        shiroFilterFactoryBean.setFilters(filterMap);

        // <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->
        filterChainDefinitionMap.put("/**", "adminFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(UserRealm userRealm, AdminRealm adminRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 设置realm
        securityManager.setRealms(Arrays.asList(adminRealm, userRealm));

        return securityManager;
    }

    /**
     * 以下为添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


}
