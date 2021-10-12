package com.zxd.www.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Makonike
 * @date 2021-10-12 14:21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 盐
     */
    private String salt;



}
