package com.zxd.www.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 题库实体类
 * @author Makonike
 * @date 2021-10-20 17:53
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionBand {

    @TableId(type = IdType.AUTO)
    private Integer questionBandId;

    private String questionBandName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
