package com.zxd.www.course.mapper;

import com.zxd.www.course.entity.CourseEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-28 23:22
 **/
@Repository
public interface CourseMapper {

    boolean insert(CourseEntity courseEntity);

    boolean delete(Integer courseId);

    CourseEntity selectById(Integer courseId);

    List<CourseEntity> selectList();

    boolean connCourseToTeacher(@Param("courseId") Integer courseId, @Param("teacherId") Integer teacherId);

    boolean connCourseToClass(@Param("courseId") Integer courseId, @Param("classId") Integer classId);

    List<CourseEntity> getByTeacherId(Integer teacherId);

    List<CourseEntity> getByClassId(Integer classId);
}
