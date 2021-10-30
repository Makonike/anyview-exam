package com.zxd.www.course.service;

import com.zxd.www.course.entity.CourseEntity;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-29 0:08
 **/
public interface CourseService {

    boolean add(CourseEntity courseEntity);

    boolean delete(Integer courseId);

    CourseEntity getById(Integer courseId);

    List<CourseEntity> getList();

    boolean connCourseToTeacher(Integer courseId, Integer teacherId);

    boolean connCourseToClass(Integer courseId, Integer classId);

    List<CourseEntity> getByTeacherId(Integer teacherId);

    List<CourseEntity> getByClassId(Integer classId);

    boolean update(CourseEntity courseEntity);

    boolean deleteTeacherCourse(Integer courseId, Integer teacherId);

    boolean deleteClassCourse(Integer courseId, Integer classId);

}
