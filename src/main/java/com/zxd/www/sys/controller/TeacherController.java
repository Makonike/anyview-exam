package com.zxd.www.sys.controller;

import com.zxd.www.global.entity.dto.JsonResponse;
import com.zxd.www.sys.entity.Teacher;
import com.zxd.www.sys.service.TeacherService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Makonike
 * @date 2021-10-15 21:34
 **/
@RestController
@RequestMapping("/sys/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 为管理员添加教师信息并绑定教师角色
     * @param teacher teacher
     * @param adminId adminId
     */
    @PostMapping("/save")
    @RequiresPermissions(value = "sys:teacher:save")
    public JsonResponse save(@RequestBody Teacher teacher, @RequestParam("adminId") Integer adminId){
        if(teacherService.save(teacher, adminId)){
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("保存失败！");
    }

    /**
     * 根据id删除教师信息
     * @param teacherId id
     */
    @DeleteMapping("/delete/{teacherId}")
    @RequiresPermissions(value = "sys:teacher:delete")
    public JsonResponse deleteById(@PathVariable("teacherId") Integer teacherId){
        if(teacherService.deleteById(teacherId)){
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("删除失败，找不到该教师信息！");
    }

    @PutMapping("/update")
    public JsonResponse update(@RequestBody Teacher teacher){
        if(teacherService.update(teacher)){
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("更新失败，未找到该教师信息");
    }

    @GetMapping("/list")
    @RequiresPermissions("sys:teacher:list")
    public JsonResponse getList(){
        return new JsonResponse().data(teacherService.getList());
    }

}
