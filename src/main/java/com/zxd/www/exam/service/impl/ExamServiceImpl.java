package com.zxd.www.exam.service.impl;

import com.zxd.www.exam.mapper.ExamMapper;
import com.zxd.www.exam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Makonike
 * @date 2021-10-15 23:26
 **/
@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    @Override
    public String getExamNoByUserId(Integer userId) {
        return examMapper.getExamNoByUserId(userId);
    }

    @Override
    public String getExamNoByAdminId(Integer adminId) {
        return examMapper.getExamNoByAdminId(adminId);
    }
}
