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

    /**
     * 修改主键自增策略
     * 插入user后，实体类的userid会被自动赋值
     * 解决的是 完成某些操作还要去数据库查询userid的问题
     */
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
