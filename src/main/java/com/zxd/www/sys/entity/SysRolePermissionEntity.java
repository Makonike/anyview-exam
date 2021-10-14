package com.zxd.www.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 角色与权限的中间表实体类
 * @author Makonike
 * @date 2021-10-14 18:54
 **/
@Data
public class SysRolePermissionEntity {

    @TableId(type = IdType.AUTO)
    private Integer rolePerId;
    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer permissionId;
}
