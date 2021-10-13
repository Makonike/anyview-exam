package com.zxd.www.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Makonike
 * @date 2021-10-13 19:18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @TableId(type = IdType.AUTO)
    private Integer studentId;

    /**
     * 绑定的用户id，不可更改
     */
    private Integer userId;

    /**
     * 学号
     */
    private String studentNo;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 性别 0-男 1-女
     */
    private Integer gender;

    /**
     * 班级id
     */
    private Integer classId;

    /**
     * 班级名
     */
    @TableField(exist = false)
    private String className;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否被删除 0-未删除 1-已删除
     */
    private Integer removed;



}
