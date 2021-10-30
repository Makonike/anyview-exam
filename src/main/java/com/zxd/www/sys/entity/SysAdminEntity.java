package com.zxd.www.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 管理员实体类
 * @author Makonike
 * @date 2021-10-14 16:32
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysAdminEntity {

    /**
     * 管理员id
     */
    @TableId(type = IdType.AUTO)
    private Integer adminId;

    /**
     * 管理员账号
     */
    private String adminName;

    /**
     * 管理员密码
     */
    private String adminPassword;

    /**
     * 盐
     */
    private String salt;

}
