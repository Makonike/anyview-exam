package com.zxd.www.user.controller;

import com.zxd.www.global.entity.dto.JsonResponse;
import com.zxd.www.user.entity.Student;
import com.zxd.www.user.service.StudentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * studentController
 * @author Makonike
 * @date 2021-10-13 19:31
 **/
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 根据学号获取学生信息
     * @param studentNo 学号
     */
    @GetMapping("/no")
    public JsonResponse findByStudentNo(@RequestParam("studentNo") String studentNo){
        return new JsonResponse().data(studentService.getByStudentNo(studentNo));
    }

    @GetMapping("/id/{id}")
    public JsonResponse findByStudentId(@PathVariable("id") Integer studentId){
        return new JsonResponse().data(studentService.getByStudentId(studentId));
    }

    @PutMapping("/update")
    public JsonResponse updateStudent(@RequestBody Student student){
        boolean update = studentService.update(student);
        if(update){
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("更新失败，请绑定信息后再试!");
    }

    @PostMapping("/bind")
    public JsonResponse bind(@RequestBody Student student){
        boolean bind = studentService.bind(student);
        if(bind){
            return new JsonResponse();
        }
        return new JsonResponse().unauthorized().message("更新失败,请登录后再试");
    }

    @GetMapping("/list")
    public JsonResponse list(){
        return new JsonResponse().data(studentService.getStudentList());
    }

    @PostMapping("/save/{userId}")
    @RequiresPermissions("sys:student:save")
    public JsonResponse save(@RequestBody Student student, @PathVariable("userId") Integer userId){
        boolean bind = studentService.save(student, userId);
        if(bind){
            return new JsonResponse();
        }
        return new JsonResponse().unauthorized().message("保存失败,未找到该用户或该用户已绑定信息!");
    }

    @DeleteMapping("/delete/{studentId}")
    @RequiresPermissions("sys:student:delete")
    public JsonResponse delete(@PathVariable("studentId") Integer studentId){
        if (studentService.deleteById(studentId)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("删除失败，未找到该学生！");
    }

}
