package com.zxd.www.user.service;

import com.zxd.www.user.entity.Student;

import java.util.List;

/**
 * 学生操作
 * @author Makonike
 * @date 2021-10-13 19:25
 **/
public interface StudentService {

    boolean bind(Student student);

    boolean save(Student student, Integer userId);

    Student getByStudentNo(String studentNo);

    Student getByStudentId(Integer studentId);

    List<Student> getStudentList();

    boolean update(Student student);

    boolean deleteById(Integer studentId);

}
