package com.zxd.www.sys.mapper;

import com.zxd.www.sys.entity.SysAdminEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-14 17:08
 **/
@Repository
public interface SysAdminMapper{

    boolean insert(SysAdminEntity admin);

    boolean delete(Integer adminId);

    boolean update(SysAdminEntity admin);

    List<SysAdminEntity> selectList();

    SysAdminEntity selectByAdminId(Integer adminId);

    SysAdminEntity selectByAdminName(String adminName);

    boolean bindRoleToAdmin(@Param(value = "adminId") Integer adminId
            ,@Param(value = "roleId") Integer roleId);

    Integer findRoleAdminByAdminId(Integer adminId);
}
