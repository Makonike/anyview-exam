package com.zxd.www.sys.mapper;

import com.zxd.www.sys.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-15 19:36
 **/
@Repository
public interface TeacherMapper {

    boolean save(Teacher teacher);

    boolean update(Teacher teacher);

    boolean deleteById(Integer teacherId);

    Teacher selectByTeacherId(Integer teacherId);

    Teacher selectByAdminId(Integer adminId);

    List<Teacher> selectList();

}
