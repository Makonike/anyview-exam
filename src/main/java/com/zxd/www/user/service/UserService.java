package com.zxd.www.user.service;

import com.zxd.www.user.entity.Student;
import com.zxd.www.user.entity.UserEntity;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-12 14:38
 **/
public interface UserService {

    boolean save(UserEntity userEntity);

    UserEntity getByUserId(Integer userId);

    UserEntity getByUserName(String userName);

    boolean update(UserEntity userEntity);

    boolean deleteById(Integer userId);

    Student getInfoById(Integer userId);

    List<UserEntity> getList();
}
