package com.zxd.www.exam.service;

import com.zxd.www.exam.entity.Exam;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-15 23:06
 **/
public interface ExamService {

    /**
     * 插入自动测验
     * @param exam 测验
     */
    boolean autoExamSave(Exam exam);

    /**
     * 插入手动测验
     * @param exam 测验
     */
    boolean examSave(Exam exam);

    /**
     * 手动测验准备
     * @param exam 测验
     */
    boolean examSetUp(Exam exam);

    /**
     * 自动测验准备
     * @param examId 测验id
     */
    boolean autoExamSetUp(Integer examId);

    /**
     * 手动测验开始
     * @param examId 测验id
     */
    boolean examStart(Integer examId);

    /**
     * 到时自动开始测验
     * @param examId 测验id
     */
    boolean autoExamStart(Integer examId);

    /**
     * 手动测验结束
     * @param examId 测验id
     */
    boolean examStop(Integer examId);

    /**
     * 自动测验结束
     * @param examId 测验id
     */
    boolean autoExamStop(Integer examId);

    /**
     * 测验延时
     * @param examId 测验id
     * @param delayTime 延迟时间
     */
    boolean examDelay(Integer examId,Integer delayTime);

    /**
     * 根据examId获取exam信息
     * @param examId examId
     */
    Exam getByExamId(Integer examId);

    /**
     * 修改测验编排，更改题目表
     * @param exam 测验
     */
    boolean update(Exam exam);

    /**
     * 用户获取当前测验信息
     * @param userId 用户id
     */
    Exam getByUserId(Integer userId);

    /**
     * 教师获取测验列表
     */
    List<Exam> getExamList();

}
