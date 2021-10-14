package com.zxd.www.sys.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-14 20:51
 **/
@Repository
public interface SysPermissionMapper {

    List<String> selectList();

    List<String> selectById(Integer adminId);

}
