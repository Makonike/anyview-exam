package com.zxd.www.exam.controller;

import com.zxd.www.exam.entity.Exam;
import com.zxd.www.exam.service.ExamService;
import com.zxd.www.global.entity.dto.JsonResponse;
import com.zxd.www.user.entity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Makonike
 * @date 2021-10-17 22:04
 **/
@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;


    /**
     * 设置自动测验
     * @param exam 测验
     */
    @PostMapping("/autoSave")
    public JsonResponse autoExamSave(@RequestBody Exam exam){
        if (examService.autoExamSave(exam)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("课程冲突，请重新设置！");
    }

    /**
     * 保存测验,并绑定测验班级
     * @param exam 测验
     */
    @PostMapping("/save")
    public JsonResponse save(@RequestBody Exam exam){
        if (examService.examSave(exam)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("保存错误！");

    }

    /**
     * 手动准备开始测验
     * @param exam 测验
     */
    @PutMapping("/setUp")
    public JsonResponse setUp(@RequestBody Exam exam){
        if (examService.examSetUp(exam)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("手动准备测验失败!");
    }

    /**
     * 手动开始测验
     * @param examId 测验id
     */
    @PutMapping("/start/{examId}")
    public JsonResponse start(@PathVariable("examId") Integer examId){
        if (examService.examStart(examId)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("手动开始测验失败！");
    }

    /**
     * 手动结束测验
     * @param examId 测验id
     */
    @PutMapping("/stop/{examId}")
    public JsonResponse stop(@PathVariable("examId") Integer examId){
        if (examService.examStop(examId)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("手动结束测验失败!");
    }

    /**
     * 测验延时
     * @param examId 测验id
     * @param delayTime 延迟时间
     */
    @PutMapping("/delay/{examId}/{delayTime}")
    public JsonResponse examDelay(@PathVariable("examId") Integer examId, @PathVariable("delayTime") Integer delayTime){
        if (examService.examDelay(examId, delayTime)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("课程冲突，请重新设置！");
    }

    @PutMapping("/update")
    public JsonResponse examUpdate(@RequestBody Exam exam){
        if (examService.update(exam)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("修改测验失败");
    }

    @GetMapping("/serverTime")
    public JsonResponse getServerTime(){
        return new JsonResponse().data(LocalDateTime.now());
    }

    @GetMapping("/user/get")
    public JsonResponse getByExamData(){
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        return new JsonResponse().data(examService.getByUserId(user.getUserId()));
    }

}
