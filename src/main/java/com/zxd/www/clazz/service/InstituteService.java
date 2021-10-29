package com.zxd.www.clazz.service;

import com.zxd.www.clazz.entity.InstituteEntity;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-29 18:23
 **/
public interface InstituteService {

    boolean add(InstituteEntity instituteEntity);

    boolean delete(Integer instituteId);

    boolean update(InstituteEntity instituteEntity);

    InstituteEntity getById(Integer instituteId);

    List<InstituteEntity> getList();
}
