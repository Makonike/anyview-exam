package com.zxd.www.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 测验实体类
 * @author Makonike
 * @date 2021-10-15 22:58
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {

    /**
     * 测验id
     */
    @TableId(type = IdType.AUTO)
    private Integer examId;
    /**
     * 测验编号
     */
    private String examNo;
    /**
     * 发布教师id
     */
    private Integer teacherId;
    /**
     * 开始准备时间
     */
    private LocalDateTime setupTime;
    /**
     * 开始测验时间
     */
    private LocalDateTime startTime;
    /**
     * 测验时长
     */
    private Integer examTime;
    /**
     * 测验状态，0-未开始，1-准备，2-进行中，3-已结束
     */
    private Integer status;

}
