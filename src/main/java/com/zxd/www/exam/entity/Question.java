package com.zxd.www.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 测验题目
 * @author Makonike
 * @date 2021-10-18 19:29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @TableId(type = IdType.AUTO)
    private Integer questionId;

    /**
     * 题库id
     */
    private Integer bandId;

    /**
     * 问题名
     */
    private String questionName;

    /**
     * 题目
     */
    private String topic;

    /**
     * 章节
     */
    private String sections;

    /**
     * 难度 0-打卡题 1-简单题 2-中等题 3-难题
     */
    private Integer difficulty;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
