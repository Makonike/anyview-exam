# anyview-exam



基于springboot、shiro、mybatis-plus的测验模块

前端使用了bootstrap框架

前后端分离：[后端地址](https://github.com/Makonike/anyview-exam) [前端地址](https://github.com/Makonike/anyview-exam-fore-end)

# TODO

- 教师，课程，班级关系修改
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





# 关于定时器
