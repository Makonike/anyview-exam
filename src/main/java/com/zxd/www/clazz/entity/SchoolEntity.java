package com.zxd.www.clazz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Makonike
 * @date 2021-10-29 17:41
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolEntity {

    @TableId(type = IdType.AUTO)
    private Integer schoolId;

    private String schoolName;

    private Integer removed;
}
