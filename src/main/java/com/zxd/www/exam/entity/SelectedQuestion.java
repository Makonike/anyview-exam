package com.zxd.www.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Makonike
 * @date 2021-10-18 19:37
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectedQuestion {

    private Integer selectedQuestionId;

    private Integer questionId;

    private Integer score;
}
