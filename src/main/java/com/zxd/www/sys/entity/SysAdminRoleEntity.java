package com.zxd.www.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 管理员用户与角色的中间表
 * @author Makonike
 * @date 2021-10-14 16:42
 **/
@Data
public class SysAdminRoleEntity {

    @TableId(type = IdType.AUTO)
    private Integer adminRoleId;

    private Integer adminId;

    private Integer roleId;

}
