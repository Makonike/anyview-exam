package com.zxd.www.user.service.impl;

import com.zxd.www.global.util.StringUtil;
import com.zxd.www.user.entity.Student;
import com.zxd.www.user.entity.UserEntity;
import com.zxd.www.user.mapper.UserMapper;
import com.zxd.www.user.service.UserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * @param userId userId
     */
    @Override
    public boolean deleteById(Integer userId){
        return userMapper.deleteByUserId(userId);
    }

    @Override
    public Student getInfoById(Integer userId){
        return userMapper.userInfo(userId);
    }

    @Override
    public List<UserEntity> getList() {
        return userMapper.selectList();
    }


}
