package com.zxd.www.user.service.impl;

import com.zxd.www.global.util.StringUtil;
import com.zxd.www.user.entity.UserEntity;
import com.zxd.www.user.mapper.UserMapper;
import com.zxd.www.user.service.UserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Makonike
 * @date 2021-10-12 14:38
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean save(UserEntity userEntity) {
        // 生成20位随机字符串作为盐
        String salt = StringUtil.generateSalt(20);
        // sha256加密
        userEntity.setUserPassword(new Sha256Hash(userEntity.getUserPassword(), salt).toHex());
        userEntity.setSalt(salt);
        return userMapper.save(userEntity);
    }

    @Override
    public UserEntity getByUserId(Integer userId) {
        return userMapper.selectByUserId(userId);
    }

    @Override
    public UserEntity getByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    @Override
    public boolean update(UserEntity userEntity) {
        // 生成20位随机字符串作为盐
        String salt = StringUtil.generateSalt(20);
        // sha256加密
        userEntity.setUserPassword(new Sha256Hash(userEntity.getUserPassword(), salt).toHex());
        userEntity.setSalt(salt);
        return userMapper.update(userEntity);
    }

    /**
     * TODO: 批量删除用户
     * @param adminIds adminIds
     */
    @Override
    public boolean deleteBatch(Integer[] adminIds){
            return false;
    }


}
