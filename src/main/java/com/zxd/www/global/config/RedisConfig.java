package com.zxd.www.global.config;

import com.zxd.www.websocket.constant.WebSocketConstant;
import com.zxd.www.websocket.handler.impl.RedisReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.*;

/**
 * Redis配置类
 * @author Makonike
 */
@Configuration
public class RedisConfig {


    @Autowired
    private RedisReceiver redisReceiver;


    /**
     * RedisTemplate配置
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        // 设置序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        // 配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
        // key序列化
        redisTemplate.setKeySerializer(stringSerializer);
        // value序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // Hash key序列化
        redisTemplate.setHashKeySerializer(stringSerializer);
        // Hash value序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    /**
     * 创建消息监听容器
     * @param redisConnectionFactory redisConnectionFactory
     */
    @Bean
    public RedisMessageListenerContainer getRedisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        // 添加消息队列监听
        container.addMessageListener(messageListenerAdapter(), new PatternTopic(WebSocketConstant.SEND_ONE_CHANNEL));
        // 监听所有
        container.addMessageListener(messageAllListenerAdapter(), new PatternTopic(WebSocketConstant.SEND_ALL_CHANNEL));
        // 监听单点登录
        container.addMessageListener(messageNotifyOnlineAdapter(), new PatternTopic(WebSocketConstant.NOTIFY_ONLINE_CHANNEL));
        // 监听关闭定时器
        container.addMessageListener(messageAllListenerAdapter(), new PatternTopic(WebSocketConstant.STOP_SCHEDULED_CHANNEL));
        return container;
    }

    /**
     * 消息监听适配器
     * 添加订阅消息处理类，反射获取处理类中的处理方法
     */
    @Bean
    public MessageListenerAdapter messageListenerAdapter(){
        return new MessageListenerAdapter(redisReceiver, WebSocketConstant.SEND_ONE_METHOD_NAME);
    }

    @Bean
    public MessageListenerAdapter messageAllListenerAdapter(){
        return new MessageListenerAdapter(redisReceiver, WebSocketConstant.SEND_ALL_METHOD_NAME);
    }

    @Bean
    public MessageListenerAdapter messageNotifyOnlineAdapter(){
        return new MessageListenerAdapter(redisReceiver, WebSocketConstant.NOTIFY_ONLINE);
    }

    @Bean
    public MessageListenerAdapter messageStopScheduledTask(){
        return new MessageListenerAdapter(redisReceiver, WebSocketConstant.STOP_SCHEDULED);
    }

}



















