package com.zxd.www.sys.mapper;

import com.zxd.www.sys.entity.SysRoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-14 19:46
 **/
@Repository
public interface SysRoleMapper {

    /**
     * 查询admin的角色名
     * @param adminId adminId
     */
    List<String> selectById(Integer adminId);

    /**
     * 查询所有角色名
     */
    List<String> selectList();


}
