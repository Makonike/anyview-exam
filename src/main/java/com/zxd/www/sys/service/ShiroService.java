package com.zxd.www.sys.service;

import com.zxd.www.sys.entity.SysAdminEntity;

import java.util.Set;

/**
 * @author Makonike
 * @date 2021-10-14 18:42
 **/
public interface ShiroService {

    SysAdminEntity getByName(String adminName);

    Set<String> getRoles(Integer adminId);

    Set<String> getPerms(Integer adminId);
}
