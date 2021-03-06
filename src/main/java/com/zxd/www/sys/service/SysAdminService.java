package com.zxd.www.sys.service;

import com.zxd.www.sys.entity.SysAdminEntity;
import com.zxd.www.sys.entity.Teacher;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-14 17:32
 **/
public interface SysAdminService {

    boolean save(SysAdminEntity admin);

    boolean delete(Integer adminId);

    boolean update(SysAdminEntity admin);

    List<SysAdminEntity> list();

    SysAdminEntity getById(Integer adminId);

    Teacher teacherInfo(Integer adminId);

    boolean bindAdminToAdmin(Integer adminId);

    boolean isAdminRoleAdmin(Integer adminId);
}
