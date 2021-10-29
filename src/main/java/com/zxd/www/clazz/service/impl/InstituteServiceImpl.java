package com.zxd.www.clazz.service.impl;

import com.zxd.www.clazz.entity.InstituteEntity;
import com.zxd.www.clazz.mapper.InstituteMapper;
import com.zxd.www.clazz.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-29 18:25
 **/
@Service
public class InstituteServiceImpl implements InstituteService {

    @Autowired
    private InstituteMapper instituteMapper;

    @Override
    public boolean add(InstituteEntity instituteEntity) {
        return instituteMapper.insert(instituteEntity);
    }

    @Override
    public boolean delete(Integer instituteId) {
        return instituteMapper.delete(instituteId);
    }

    @Override
    public boolean update(InstituteEntity instituteEntity) {
        return instituteMapper.update(instituteEntity);
    }

    @Override
    public InstituteEntity getById(Integer instituteId) {
        return instituteMapper.selectById(instituteId);
    }

    @Override
    public List<InstituteEntity> getList() {
        return instituteMapper.selectList();
    }
}
