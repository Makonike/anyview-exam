package com.zxd.www.sys.service.impl;

import com.zxd.www.sys.entity.SysAdminEntity;
import com.zxd.www.sys.entity.Teacher;
import com.zxd.www.sys.mapper.SysAdminMapper;
import com.zxd.www.sys.mapper.TeacherMapper;
import com.zxd.www.sys.service.TeacherService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-15 21:11
 **/
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private SysAdminMapper adminMapper;

    /**
     * 为管理员绑定teacher,并为管理员添加teacher角色
     * @param teacher teacher
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public boolean save(Teacher teacher, Integer adminId) {
        teacher.setAdminId(adminId);
        adminMapper.bindRoleToAdmin(adminId, 2);
        return teacherMapper.save(teacher);
    }

    @Override
    public boolean update(Teacher teacher) {
        return teacherMapper.update(teacher);
    }

    @Override
    public boolean deleteById(Integer teacherId) {
        return teacherMapper.deleteById(teacherId);
    }

    @Override
    public Teacher getByTeacherId(Integer teacherId) {
        return teacherMapper.selectByTeacherId(teacherId);
    }

    @Override
    public List<Teacher> getList() {
        return teacherMapper.selectList();
    }

    @Override
    public Teacher getByAdminId(Integer adminId) {
        return teacherMapper.selectByAdminId(adminId);
    }
}
