package com.zxd.www.scheduler.service;

import com.zxd.www.global.constant.RedisConstant;
import com.zxd.www.global.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
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
    private RedisUtil redisUtil;

    @Autowired
    private ExamScheduledTaskService scheduledTaskService;

    public ScheduledTask(final ThreadPoolTaskScheduler threadPoolTaskScheduler){
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    /**
     * 当前服务器的标识
     */
    public static String requestId;

    public static Map<String, ScheduledFuture<?>> future;

    static {
        future = new HashMap<>();
        requestId = UUID.randomUUID().toString().substring(0,15);
    }

    /**
     * 启动定时器
     * 当保存测验时，轮询数据库向教师端发送测验信息
     */
    public void startTask(Integer examId){
        // 只有拿到锁的服务端才能进行有效的定时任务
        if(redisUtil.setIfAbsent(".lock" + examId, requestId, RedisConstant.LOCK_KEY_TIME)){
            log.info("===定时任务" + examId + "开始执行===");
        }
        // 解决重复定时器问题
        if(future.get(examId.toString()) != null){
            return ;
        }

        // 第一个参数是定时任务执行的方法，第二个为定时任务触发器，设置定时任务执行的时间
        future.put(examId.toString(),threadPoolTaskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                // 如果处理定时任务的服务器（其他）宕机，此处就能拿到锁
                if (redisUtil.setIfAbsent(".lock" + examId, requestId, RedisConstant.LOCK_KEY_TIME)) {
                    log.info("===定时任务" + examId + "持续执行===");
                }
                // 如果是当前服务器获取到锁
                if(requestId.equals(redisUtil.get(".lock" + examId))){
                    scheduledTaskService.examStartSendMessage(examId);
                    redisUtil.expire(".lock" + examId, RedisConstant.LOCK_SCHEDULED_TIME);
                }
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
                    if(requestId.equals(redisUtil.get(examId + ".lock"))){
                        log.info("===定时任务" + examId + "执行完成===");
                        redisUtil.del(examId+".lock");
                    }
                }
            }
        }
    }

    public void stopTask(Integer examId){
        endTask(examId);
        scheduledTaskService.examStopSendMessage(examId);
    }



}
