package com.zxd.www.clazz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Makonike
 * @date 2021-10-29 18:15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstituteEntity {

    @TableId(type = IdType.AUTO)
    private Integer instituteId;

    private String instituteName;

    private Integer removed;

}
