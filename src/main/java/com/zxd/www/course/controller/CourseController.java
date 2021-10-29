package com.zxd.www.course.controller;

import com.zxd.www.course.entity.CourseEntity;
import com.zxd.www.course.service.CourseService;
import com.zxd.www.global.entity.dto.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Makonike
 * @date 2021-10-29 0:34
 **/
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/save")
    public JsonResponse add(@RequestBody CourseEntity courseEntity){
        if (courseService.add(courseEntity)) {
            return new JsonResponse();
        }
        return new JsonResponse().unauthorized().message("添加课程失败！");
    }

    @DeleteMapping("/delete/{courseId}")
    public JsonResponse delete(@PathVariable("courseId") Integer courseId){
        if (courseService.delete(courseId)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("删除失败，找不到该课程！");
    }

    @PutMapping("/update")
    public JsonResponse update(@RequestBody CourseEntity courseEntity){
        if (courseService.update(courseEntity)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("找不到该课程!");
    }

    @GetMapping("/get/id/{courseId}")
    public JsonResponse getById(@PathVariable("courseId") Integer courseId){
        return new JsonResponse().data(courseService.getById(courseId));
    }

    @GetMapping("/get/list")
    public JsonResponse getList(){
        return new JsonResponse().data(courseService.getList());
    }

    @PostMapping("/conn/teacher/{courseId}/{teacherId}")
    public JsonResponse connCourseToTeacher(@PathVariable("courseId") Integer courseId, @PathVariable("teacherId") Integer teacherId){
        if (courseService.connCourseToTeacher(courseId, teacherId)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("已存在该课程, 无法重复添加！");
    }

    @PostMapping("/conn/class/{courseId}/{classId}")
    public JsonResponse connCourseToClass(@PathVariable("courseId") Integer courseId, @PathVariable("classId") Integer classId){
        if (courseService.connCourseToClass(courseId, classId)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("已存在该课程, 无法重复添加！");
    }

    @GetMapping("/get/teacher/{teacherId}")
    public JsonResponse getByTeacherId(@PathVariable("teacherId") Integer teacherId){
        return new JsonResponse().data(courseService.getByTeacherId(teacherId));
    }

    @GetMapping("/get/class/{classId}")
    public JsonResponse getByClassId(@PathVariable("classId") Integer classId){
        return new JsonResponse().data(courseService.getByClassId(classId));
    }
}
