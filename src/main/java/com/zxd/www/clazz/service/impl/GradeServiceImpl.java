package com.zxd.www.clazz.service.impl;

import com.zxd.www.clazz.entity.GradeEntity;
import com.zxd.www.clazz.mapper.GradeMapper;
import com.zxd.www.clazz.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-30 14:00
 **/
@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<GradeEntity> getList() {
        return gradeMapper.selectList();
    }
}
