package com.zxd.www.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Makonike
 * @date 2021-10-18 19:29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    private Integer questionId;

    private String questionName;

    private String topic;
}
