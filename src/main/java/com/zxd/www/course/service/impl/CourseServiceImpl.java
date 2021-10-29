package com.zxd.www.course.service.impl;

import com.zxd.www.course.entity.CourseEntity;
import com.zxd.www.course.mapper.CourseMapper;
import com.zxd.www.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-29 0:16
 **/
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public boolean add(CourseEntity courseEntity) {
        return courseMapper.insert(courseEntity);
    }

    @Override
    public boolean delete(Integer courseId) {
        return courseMapper.delete(courseId);
    }

    @Override
    public CourseEntity getById(Integer courseId) {
        return courseMapper.selectById(courseId);
    }

    @Override
    public List<CourseEntity> getList() {
        return courseMapper.selectList();
    }

    /**
     * 为教师添加课程
     * @param courseId 课程id
     * @param teacherId 教师id
     */
    @Override
    public boolean connCourseToTeacher(Integer courseId, Integer teacherId) {
        List<CourseEntity> contains = getByTeacherId(teacherId);
        CourseEntity course = getById(courseId);
        // 已存在就不再添加
        if (contains.contains(course)) {
            return false;
        }
        return courseMapper.connCourseToTeacher(courseId, teacherId);
    }

    /**
     * 为班级添加课程
     * @param courseId 课程id
     * @param classId 教师id
     */
    @Override
    public boolean connCourseToClass(Integer courseId, Integer classId) {
        List<CourseEntity> classes = getByClassId(classId);
        CourseEntity course = getById(courseId);
        // 已存在就不再添加
        if(classes.contains(course)){
            return false;
        }
        return courseMapper.connCourseToClass(courseId, classId);
    }

    /**
     * 获取教师所教的所有课程
     * @param teacherId 教师id
     */
    @Override
    public List<CourseEntity> getByTeacherId(Integer teacherId) {
        return courseMapper.getByTeacherId(teacherId);
    }

    /**
     * 获取班级的所有课程
     * @param classId 班级id
     */
    @Override
    public List<CourseEntity> getByClassId(Integer classId) {
        return courseMapper.getByClassId(classId);
    }

    @Override
    public boolean update(CourseEntity courseEntity) {
        return courseMapper.update(courseEntity);
    }
}
