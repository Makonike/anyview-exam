package com.zxd.www.clazz.service.impl;

import com.zxd.www.clazz.entity.SchoolEntity;
import com.zxd.www.clazz.mapper.SchoolMapper;
import com.zxd.www.clazz.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-29 17:56
 **/
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public boolean add(SchoolEntity schoolEntity) {
        return schoolMapper.insert(schoolEntity);
    }

    @Override
    public boolean delete(Integer schoolId) {
        return schoolMapper.delete(schoolId);
    }

    @Override
    public SchoolEntity getById(Integer schoolId) {
        return schoolMapper.selectById(schoolId);
    }

    @Override
    public List<SchoolEntity> getList() {
        return schoolMapper.selectList();
    }

    @Override
    public boolean update(SchoolEntity schoolEntity) {
        return schoolMapper.update(schoolEntity);
    }
}
