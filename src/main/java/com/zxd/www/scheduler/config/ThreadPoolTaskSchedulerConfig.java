package com.zxd.www.scheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 定时任务调度线程池配置类
 * 创建ThreadPoolTaskScheduler实例并交给spring容器管理
 * @author Makonike
 * @date 2021-10-25 13:48
 **/
@Configuration
public class ThreadPoolTaskSchedulerConfig {

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        return new ThreadPoolTaskScheduler();
    }

}
