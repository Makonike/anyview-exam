package com.zxd.www.scheduler.service;

/**
 * @author Makonike
 * @date 2021-10-25 14:19
 **/
public interface ExamScheduledTaskService {

    void examStartSendMessage(Integer examId);

    void examStopSendMessage(Integer examId);
}
