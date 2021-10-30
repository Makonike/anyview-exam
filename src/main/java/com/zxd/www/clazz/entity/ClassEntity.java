package com.zxd.www.clazz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 班级实体类
 * @author Makonike
 * @date 2021-10-16 13:32
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassEntity {

    /**
     * 班级号
     */
    @TableId(type = IdType.AUTO)
    private Integer classId;

    /**
     * 班级名
     */
    private String className;

    /**
     * 学校id
     */
    private Integer schoolId;

    @TableField(exist = false)
    private String schoolName;

    /**
     * 学院id
     */
    private Integer instituteId;

    /**
     * 学院名
     */
    @TableField(exist = false)
    private String instituteName;

    /**
     * 年级id
     */
    private Integer gradeId;

    @TableField(exist = false)
    private String gradeName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 课程描述
     */
    private String classDesc;

    /**
     * 是否删除 0-未 1-已删除
     */
    private Integer removed;


}
