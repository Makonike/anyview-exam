package com.zxd.www.sys.service.impl;

import com.zxd.www.global.constant.CommonConstant;
import com.zxd.www.sys.entity.SysAdminEntity;
import com.zxd.www.sys.mapper.SysAdminMapper;
import com.zxd.www.sys.mapper.SysPermissionMapper;
import com.zxd.www.sys.mapper.SysRoleMapper;
import com.zxd.www.sys.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Makonike
 * @date 2021-10-14 18:46
 **/
@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private SysAdminMapper adminMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public SysAdminEntity getByName(String adminName) {
        return adminMapper.selectByAdminName(adminName);
    }

    @Override
    public Set<String> getRoles(Integer adminId) {
        List<String> roles = null;
        if(adminId.equals(CommonConstant.SUPER_ADMIN_ID)){
            // 超级管理员有所有角色
            roles = sysRoleMapper.selectList();
        } else {
            roles = sysRoleMapper.selectById(adminId);
        }
        return new HashSet<>(roles);
    }

    @Override
    public Set<String> getPerms(Integer adminId) {
        List<String> permissions = null;
        if(adminId.equals(CommonConstant.SUPER_ADMIN_ID)){
            // 超级管理员有所有权限
            permissions = sysPermissionMapper.selectList();
        } else {
            permissions = sysPermissionMapper.selectById(adminId);
        }
        return new HashSet<>(permissions);
    }
}
