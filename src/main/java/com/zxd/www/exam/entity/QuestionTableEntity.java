package com.zxd.www.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 测验题目表
 * @author Makonike
 * @date 2021-10-18 19:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionTableEntity {

    @TableId(type = IdType.AUTO)
    private Integer questionTableId;

    private String tableName;

    private Integer teacherId;

    private LocalDateTime createTime;

    private Integer removed;

}
