package com.zxd.www.exam.mapper;

import com.zxd.www.exam.entity.Exam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * @author Makonike
 * @date 2021-10-15 23:06
 **/
@Repository
public interface ExamMapper {

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
     * @param exam 测验
     */
    boolean examStart(Exam exam);

    /**
     * 自动开启测验
     * @param examId 测试id
     */
    boolean autoExamStart(Integer examId);

    /**
     * 自动测验结束
     * @param examId 测验id
     */
    boolean autoExamStop(Integer examId);

    /**
     * 手动测验结束
     * @param exam 测验
     */
    boolean examStop(Exam exam);

    /**
     * 测验延时
     * @param exam 测验
     */
    boolean examDelay(Exam exam);


    Exam getByExamId(Integer examId);

    /**
     * 搜索会冲突的测验数
     * @param classId 班级号
     * @param setupTime 准备测验时间
     * @param expTime 结束测验时间
     */
    Integer countExamPeriod(@Param("classId") Integer classId,@Param("setupTime") LocalDateTime setupTime
            , @Param("expTime") LocalDateTime expTime);

}
