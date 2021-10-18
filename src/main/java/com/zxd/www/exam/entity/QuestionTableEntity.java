package com.zxd.www.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Makonike
 * @date 2021-10-18 19:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionTableEntity {

    private Integer questionTableId;

    private String tableName;

}
