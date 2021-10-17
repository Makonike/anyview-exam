package com.zxd.www.exam.controller;

import com.zxd.www.exam.entity.Exam;
import com.zxd.www.exam.service.ExamService;
import com.zxd.www.global.entity.dto.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Makonike
 * @date 2021-10-17 22:04
 **/
@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping("/delay/{examId}/{delayTime}")
    public JsonResponse examDelay(@PathVariable("examId") Integer examId, @PathVariable("delayTime") Integer delayTime){
        if (examService.examDelay(examId, delayTime)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("课程冲突，请重新设置！");
    }

    @PostMapping("/autoSave")
    public JsonResponse autoExamSave(@RequestBody Exam exam){
        if (examService.autoExamSave(exam)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("课程冲突，请重新设置！");
    }

    @PostMapping("/save")
    public JsonResponse save(@RequestBody Exam exam){
        if (examService.examSave(exam)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("保存错误！");

    }


}
