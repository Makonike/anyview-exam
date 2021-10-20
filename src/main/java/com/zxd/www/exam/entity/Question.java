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

    private String questionName;

    private String topic;

    private String sections;

    private Integer difficulty;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
