package com.zxd.www.scheduler.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * 基于线程池的定时任务调度类
 * @author Makonike
 * @date 2021-10-25 13:50
 **/
@Component
@Slf4j
public class ScheduledTask {

    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private ExamScheduledTaskService scheduledTaskService;

    public ScheduledTask(final ThreadPoolTaskScheduler threadPoolTaskScheduler){
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    public static Map<String, ScheduledFuture<?>> future;

    static {
        future = new HashMap<>();
    }

    /**
     * 启动定时器
     * 当保存测验时，轮询数据库向教师端发送测验信息
     */
    public void startTask(Integer examId){
        // 第一个参数是定时任务执行的方法，第二个为定时任务触发器，设置定时任务执行的时间
        log.info("===定时任务" + examId + "开始执行===");
        future.put(examId.toString(),threadPoolTaskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                scheduledTaskService.examStartSendMessage(examId);
            }
            // 每五秒发送一次
        }, new CronTrigger("0/5 * * * * *")));
    }

    /**
     * 停止定时器
     * 设计成接口，如果前端收到信息后发个请求来停止定时器
     */
    public void endTask(Integer examId){
        if(future != null){
            ScheduledFuture<?> scheduledFuture = future.get(examId.toString());
            if(scheduledFuture != null){
                if (scheduledFuture.cancel(true)) {
                    log.info("===定时任务" + examId + "执行完成===");

                }
            }
        }
    }

    public void stopTask(Integer examId){
        scheduledTaskService.examStopSendMessage(examId);
        endTask(examId);
    }

}
