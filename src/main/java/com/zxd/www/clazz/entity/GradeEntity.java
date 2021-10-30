package com.zxd.www.clazz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Makonike
 * @date 2021-10-30 13:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeEntity {

    @TableId(type = IdType.AUTO)
    private Integer gradeId;

    private String gradeName;

    private Integer removed;
}
