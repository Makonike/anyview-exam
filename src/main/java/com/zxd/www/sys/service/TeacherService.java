package com.zxd.www.sys.service;

import com.zxd.www.sys.entity.Teacher;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-15 21:11
 **/
public interface TeacherService {

    boolean save(Teacher teacher, Integer adminId);

    boolean update(Teacher teacher);

    boolean deleteById(Integer teacherId);

    Teacher getByTeacherId(Integer teacherId);

    List<Teacher> getList();

    Teacher getByAdminId(Integer adminId);

}
