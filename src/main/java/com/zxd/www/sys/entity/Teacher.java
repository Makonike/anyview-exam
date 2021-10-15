package com.zxd.www.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Makonike
 * @date 2021-10-15 19:29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    /**
     * 教师id
     */
    @TableId(type = IdType.AUTO)
    private Integer teacherId;

    /**
     * 管理员id
     */
    private Integer adminId;

    /**
     * 教师姓名
     */
    private String teacherName;

    /**
     * 性别 0-男 1-女
     */
    private Integer gender;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否被移除 0-否 1-是
     */
    private Integer removed;
}
