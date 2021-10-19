package com.zxd.www.global.listener;

import com.zxd.www.exam.service.ExamService;
import com.zxd.www.global.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @author Makonike
 * @date 2021-10-18 21:38
 **/
@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Autowired
    private ExamService examService;


    /**
     * 需要修改redis配置
     * 将默认配置notify-keyspace-events的值为"",修改为 notify-keyspace-events Ex
     */
    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据失效事件，进行数据处理
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 生效的key
        String key=message.toString();
        // 从失效key中筛选出相应的失效的key
        // DONE: 区分各种操作的key
        if (key!=null && key.startsWith(RedisConstant.PREFIX_EXAM_SETUP)){
            //截取examId，调用相关方法
            String examId = key.substring(11);
            log.info("测验id:{}进入准备状态",examId);
            examService.autoExamSetUp(Integer.valueOf(examId));
        }
        if (key!=null && key.startsWith(RedisConstant.PREFIX_EXAM_START)){
            String examId = key.substring(11);
            log.info("测验id:{}进入开始状态",examId);
            examService.autoExamStart(Integer.valueOf(examId));
        }
        if (key!=null && key.startsWith(RedisConstant.PREFIX_EXAM_STOP)){
            String examId = key.substring(10);
            log.info("测验id:{}进入结束状态",examId);
            examService.autoExamStop(Integer.valueOf(examId));
        }
    }
}
