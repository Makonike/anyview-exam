package com.zxd.www.exam.controller;

import com.zxd.www.exam.entity.SelectedQuestion;
import com.zxd.www.exam.service.SelectedQuestionService;
import com.zxd.www.global.entity.dto.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-21 16:35
 **/
@RestController
@RequestMapping("/exam/select")
public class SelectedQuestionController {

    @Autowired
    private SelectedQuestionService questionService;

    @PostMapping("/add")
    public JsonResponse add(@RequestBody List<SelectedQuestion> questions){
        if (questionService.add(questions)) {
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

    @GetMapping("/list/{tableId}")
    public JsonResponse getList(@PathVariable("tableId") Integer tableId){
        return new JsonResponse().data(questionService.getByTableId(tableId));
    }

    @GetMapping("/get/{tableId}")
    public JsonResponse get(@PathVariable("tableId") Integer tableId){
        return new JsonResponse().data(questionService.getByTableId(tableId));
    }
}
