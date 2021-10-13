package com.zxd.www.user.mapper;

import com.zxd.www.user.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-13 19:23
 **/
@Repository
public interface StudentMapper {

    boolean insertStudent(Student student);

    Student selectByStudentNo(String studentNo);

    Student selectByStudentId(Integer studentId);

    List<Student> getStudentList();

    boolean updateStudent(Student student);

    boolean deleteById(Integer studentId);

}
