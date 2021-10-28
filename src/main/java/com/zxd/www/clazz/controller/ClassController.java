package com.zxd.www.clazz.controller;

import com.zxd.www.clazz.service.ClassService;
import com.zxd.www.global.entity.dto.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Makonike
 * @date 2021-10-24 16:09
 **/
@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("/list")
    public JsonResponse teacherGetList(){
        return new JsonResponse().data(classService.getListByTeacher());
    }

    @GetMapping("/get")
    public JsonResponse getList(){
        return new JsonResponse().data(classService.getList());
    }

    @GetMapping("/admin/get/{classId}")
    public JsonResponse getById(@PathVariable("classId") Integer classId){
        return new JsonResponse().data(classService.getByClassId(classId));
    }

}
