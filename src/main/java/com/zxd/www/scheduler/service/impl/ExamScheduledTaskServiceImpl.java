package com.zxd.www.scheduler.service.impl;

import com.alibaba.fastjson.JSON;
import com.zxd.www.exam.entity.Exam;
import com.zxd.www.exam.service.ExamService;
import com.zxd.www.scheduler.service.ExamScheduledTaskService;
import com.zxd.www.sys.entity.Teacher;
import com.zxd.www.sys.service.SysAdminService;
import com.zxd.www.sys.service.TeacherService;
import com.zxd.www.websocket.constant.WebSocketConstant;
import com.zxd.www.websocket.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测验定时任务调度类
 * @author Makonike
 * @date 2021-10-25 14:19
 **/
@Service
@Slf4j
public class ExamScheduledTaskServiceImpl implements ExamScheduledTaskService {

    @Autowired
    private ExamService examService;

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 给教师发送测验信息
     * @param examId 测验id
     */
    @Override
    public void examStartSendMessage(Integer examId) {
        Exam exam = examService.getByExamId(examId);
        Teacher teacher = teacherService.getByTeacherId(exam.getTeacherId());
        // 发送的信息是经过两层json序列化的，传到前端需要解析一次
        webSocketService.sendMessageById(WebSocketConstant.ADMIN_GROUP_ID, teacher.getAdminId().toString(), JSON.toJSONString(exam));
    }

    /**
     * 给其他服务端发送停止定时器的信息
     * @param examId 测验id
     */
    @Override
    public void examStopSendMessage(Integer examId) {
        webSocketService.sendStopSchedulerMessage(examId.toString());
    }

    @Override
    public void startScheduledSendMessage(Integer examId) {
        webSocketService.sendStartSchedulerMessage(examId.toString());
    }
}
