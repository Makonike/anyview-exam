package com.zxd.www.course.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程实体类
 * @author Makonike
 * @date 2021-10-28 23:21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {

    @TableId(type = IdType.AUTO)
    private Integer courseId;

    private String courseName;

    private Integer removed;

}
