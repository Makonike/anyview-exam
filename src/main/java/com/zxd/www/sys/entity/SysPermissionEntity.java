package com.zxd.www.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * permission实体类
 * @author Makonike
 * @date 2021-10-14 18:52
 **/
@Data
public class SysPermissionEntity {

    /**
     * permission-id
     */
    @TableId(type = IdType.AUTO)
    private Integer permissionId;

    /**
     * 权限字符串
     */
    private String permissionOperation;
}
