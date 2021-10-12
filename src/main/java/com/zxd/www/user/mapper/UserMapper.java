package com.zxd.www.user.mapper;

import com.zxd.www.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Makonike
 * @date 2021-10-12 14:37
 **/
@Repository
public interface UserMapper {

    boolean save(UserEntity userEntity);

    UserEntity selectByUserId(Integer userId);

    UserEntity selectByUserName(String userName);

    boolean update(UserEntity userEntity);
}
