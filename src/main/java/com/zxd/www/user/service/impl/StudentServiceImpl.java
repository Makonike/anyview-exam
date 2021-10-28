package com.zxd.www.user.service.impl;

import com.zxd.www.user.entity.Student;
import com.zxd.www.user.entity.UserEntity;
import com.zxd.www.user.mapper.StudentMapper;
import com.zxd.www.user.service.StudentService;
import com.zxd.www.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-13 19:30
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UserService userService;

    /**
     * 用户绑定
     * @param student 学生实体
     */
    @Override
    public boolean bind(Student student) {
        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        student.setStudentNo(userEntity.getUserName());
        student.setUserId(userEntity.getUserId());
        return studentMapper.insertStudent(student);
    }

    /**
     * 管理员插入学生
     * @param student student
     */
    @Override
    public boolean save(Student student, Integer userId) {
        UserEntity user = userService.getByUserId(userId);
        if(user == null){
            return false;
        }
        Student hasStudent = userService.getInfoById(userId);
        if(hasStudent != null){
            return false;
        }
        student.setUserId(userId);
        student.setStudentNo(user.getUserName());
        return studentMapper.insertStudent(student);
    }

    /**
     * 根据学号获取学生信息
     * @param studentNo 学号
     */
    @Override
    public Student getByStudentNo(String studentNo) {
        return studentMapper.selectByStudentNo(studentNo);
    }

    /**
     * 根据学生id获取学生信息
     * @param studentId 学生id
     */
    @Override
    public Student getByStudentId(Integer studentId) {
        return studentMapper.selectByStudentId(studentId);
    }

    /**
     * 获取所有学生信息
     */
    @Override
    public List<Student> getStudentList() {
        return studentMapper.getStudentList();
    }

    /**
     * 更新学生信息
     * @param student student
     */
    @Override
    public boolean update(Student student) {
        return studentMapper.updateStudent(student);
    }

    /**
     * 根据学生id删除学生信息
     * @param studentId 学生id
     */
    @Override
    public boolean deleteById(Integer studentId) {
        return studentMapper.deleteById(studentId);
    }
}
