package com.zxd.www.clazz.service.impl;

import com.zxd.www.clazz.entity.ClassEntity;
import com.zxd.www.clazz.mapper.ClassMapper;
import com.zxd.www.clazz.service.ClassService;
import jdk.internal.dynalink.support.ClassMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-16 21:17
 **/
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Override
    public boolean save(ClassEntity classEntity) {
        return classMapper.insert(classEntity);
    }

    @Override
    public boolean update(ClassEntity classEntity) {
        return classMapper.update(classEntity);
    }

    @Override
    public boolean deleteById(Integer classId) {
        return classMapper.delete(classId);
    }

    /**
     * 获取当前学生所在班级号
     * @param userId 用户id
     */
    @Override
    public Integer getClassIdByUserId(Integer userId) {
        return classMapper.selectClassIdByUserId(userId);
    }

    @Override
    public ClassEntity getByClassId(Integer classId) {
        return classMapper.selectByClassId(classId);
    }

    @Override
    public boolean bindClassToExam(Integer classId, Integer examId) {
        return classMapper.bindClassToExam(classId, examId);
    }

    @Override
    public List<ClassEntity> getList() {
        return classMapper.selectList();
    }

    /**
     * 查找当前教师进行测验的班级号
     * @param adminId 管理员id
     */
    @Override
    public List<Integer> getExamClass(Integer adminId) {
        return classMapper.getExamClass(adminId);
    }
}
