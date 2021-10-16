package com.zxd.www.clazz.service;

import com.zxd.www.clazz.entity.ClassEntity;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-16 21:09
 **/
public interface ClassService {

    boolean save(ClassEntity classEntity);

    boolean update(ClassEntity classEntity);

    boolean deleteById(Integer classId);

    Integer getClassIdByUserId(Integer userId);

    ClassEntity getByClassId(Integer classId);

    boolean bindClassToExam(Integer classId, Integer examId);

    List<ClassEntity> getList();

    List<Integer> getExamClass(Integer adminId);



}
