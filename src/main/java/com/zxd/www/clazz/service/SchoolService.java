package com.zxd.www.clazz.service;

import com.zxd.www.clazz.entity.SchoolEntity;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-29 17:55
 **/
public interface SchoolService {

    boolean add(SchoolEntity schoolEntity);

    boolean delete(Integer schoolId);

    SchoolEntity getById(Integer schoolId);

    List<SchoolEntity> getList();

    boolean update(SchoolEntity schoolEntity);
}
