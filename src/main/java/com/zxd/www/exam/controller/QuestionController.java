package com.zxd.www.exam.controller;

import com.zxd.www.exam.entity.Question;
import com.zxd.www.exam.service.QuestionService;
import com.zxd.www.global.entity.dto.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Makonike
 * @date 2021-10-21 17:29
 **/
@RestController
@RequestMapping("/exam/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/add")
    public JsonResponse add(@RequestBody Question question){
        if (questionService.add(question)) {
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("添加题目失败！");
    }

    @DeleteMapping("/delete/{questionId}")
    public JsonResponse delete(@PathVariable("questionId") Integer questionId){
        if (questionService.delete(questionId)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("删除题目失败！");
    }

    @GetMapping("/list/{bandId}")
    public JsonResponse list(@PathVariable Integer bandId){
        return new JsonResponse().data(questionService.getByBandId(bandId));
    }

    @PutMapping("/update")
    public JsonResponse update(@RequestBody Question question){
        if (questionService.update(question)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("更新题目失败!");
    }







}
