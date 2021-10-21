package com.zxd.www.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 已选测验题目
 * @author Makonike
 * @date 2021-10-18 19:37
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectedQuestion {

    @TableId(type = IdType.AUTO)
    private Integer selectedQuestionId;

    /**
     * 原题id
     */
    private Integer questionId;

    /**
     * 分数
     */
    private Integer score;
}
