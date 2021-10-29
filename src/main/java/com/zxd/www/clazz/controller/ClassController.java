package com.zxd.www.clazz.controller;

import com.zxd.www.clazz.entity.ClassEntity;
import com.zxd.www.clazz.service.ClassService;
import com.zxd.www.global.entity.dto.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/admin/list")
    public JsonResponse adminGetList(){
        return new JsonResponse().data(classService.getList());
    }

    @GetMapping("/admin/get/{classId}")
    public JsonResponse getById(@PathVariable("classId") Integer classId){
        return new JsonResponse().data(classService.getByClassId(classId));
    }

    @PostMapping("/save")
    public JsonResponse save(@RequestBody ClassEntity classEntity){
        if (classService.save(classEntity)) {
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("保存班级");
    }

    @PutMapping("/update")
    public JsonResponse update(@RequestBody ClassEntity classEntity){
        if (classService.update(classEntity)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("找不到该班级!");
    }

    @DeleteMapping("/delete/{classId}")
    public JsonResponse delete(@PathVariable("classId") Integer classId){
        if (classService.deleteById(classId)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("找不到该班级！");
    }


}
