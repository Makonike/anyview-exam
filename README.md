# anyview-exam



基于springboot、shiro、mybatis-plus的测验模块

前端使用了bootstrap框架

前后端分离：[后端地址](https://github.com/Makonike/anyview-exam) [前端地址](https://github.com/Makonike/anyview-exam-fore-end) [API文档](https://space-0hroqq.w.eolink.com/share/index?shareCode=thzqNC)
# TODO

- 前后端分页（暂时不做）

- 管理员与教师分开登录/登录反馈跳转页面
- 分值列的最后一个单元格是分值之和，需要自动计算（暂时不做）

- 复制题目表

# DONE

- 使用shiro进行认证授权
- websocket连接使用jwt鉴权

- 集群环境下的教师学生单点登录
- 学生端测验的计时器校准(http轮询)，接受信息跳转

- 教师查看测验编排、添加测验编排
- 后端定时器轮询数据库获取/每次测验状态更改 发 message 给教师端，然后jq改变测验列表的相关属性(后端定时器轮询+websocket推送)

- session里存储tableId，跳转到添加题目页面添加
- 添加测验题目表、添加题目、查看题库等页面

- 删除题目表，修改题目表名
- 分布式的定时任务

- 管理员操作学校、班级、学生、课程和教师 
- 教师，课程，班级关系修改


# 流程

打两个jar包，模拟集群服务器（8080，8081）

登录学生1（3120005180），然后再第二客户端重复登录，展示单点操作

登录学生2（3120005177），使得两个学生在两个不同服务端

登录教师1（suqin）



教师1新增测验，然后手动准备测验，发送公告，展示websocket的集群效果

教师端开始测验，查看f12 有后端通过websocket主动回传的测验信息

查看学生端1应该有弹窗提示，点击后跳转至测验界面

学生2重新登录，进入主页面，

查看学生端的network，可以发现是http轮询检查是否有测验安排

短暂等待后有弹窗提示 并跳转页面



然后延迟测验查看学生端的弹窗提示和倒计时改变，

教师结束测验，学生端有弹窗跳转回主页面



测试设置自动测验



# 关于websocket
通过redis的消息发布订阅实现websocket的集群式

所有服务端都订阅所有的redis频道

需要群发时，websocket将消息发送往某个频道，这样所有服务端都能接收到

当客户端通过redis channel接收到消息后，都试着去往客户端发送消息

若服务端本地存储有该客户端的session，则发送成功，否则忽略




# 关于定时器

采用了基于线程池的定时任务调度
基本流程如下：
## 基本流程
当教师保存自动测验。或者手动准备一个未开始的测验时，定时任务开始执行
此时定时任务由处理教师保存自动测验和准备测验操作的服务端来执行

当教师端接收到测验结束，并且修改了测验列表的属性（样式）时，发送请求停止服务端的定时任务调度

接受到请求的服务端先发送信息给其他服务端都去停止该定时任务，收到信息的服务端去查看本地是否存在该定时任务，若存在则停止，否则忽略

## 分布式定时器
同样基于redis发布订阅功能实现

当一个服务端开启定时任务时，先发送信息通知其他服务端也去执行这个定时任务

每个服务端都生成了一个唯一的标识，作为分布式锁的value，只有持有该锁的服务端才能够执行有效的定时任务

```java
public class ScheduledTask {
    // ...
    // 第一个参数是定时任务执行的方法，第二个为定时任务触发器，设置定时任务执行的时间
    future.put(examId.toString(),threadPoolTaskScheduler.schedule(new
        Runnable() {
            @Override
            public void run () {
                // 如果处理定时任务的服务器（其他）宕机，此处就能拿到锁
                if (redisUtil.setIfAbsent(".lock" + examId, requestId, RedisConstant.LOCK_KEY_TIME)) {
                    log.info("===定时任务" + examId + "持续执行===");
                }
                // 如果是当前服务器获取到锁
                if (requestId.equals(redisUtil.get(".lock" + examId))) {
                    scheduledTaskService.examStartSendMessage(examId);
                    redisUtil.expire(".lock" + examId, RedisConstant.LOCK_SCHEDULED_TIME);
                }
            }
            // 每五秒发送一次
        },new CronTrigger("0/5 * * * * *")));
    // ...
}
```

如上代码所示，如果持有该锁的服务器宕机了，其余服务器就会去争夺锁，持续执行定时任务


# PS
学生端的倒计时采用了http轮询，每八秒发送请求给后端校准时间，而教师端则是后端通过websocket通过定时任务主动发送测验信息给前端





