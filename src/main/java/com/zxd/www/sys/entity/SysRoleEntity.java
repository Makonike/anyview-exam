package com.zxd.www.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

/**
 * 角色实体类
 * @author Makonike
 * @date 2021-10-14 18:51
 **/
@Data
public class SysRoleEntity {

    /**
     * 角色id
     */
    @TableId(type = IdType.AUTO)
    private Integer roleId;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 所有的权限id集合
     */
    @TableField(exist = false)
    private List<Integer> permissionIdList;

}
