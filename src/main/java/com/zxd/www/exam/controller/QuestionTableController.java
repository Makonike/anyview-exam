package com.zxd.www.exam.controller;

import com.zxd.www.exam.entity.QuestionTableEntity;
import com.zxd.www.exam.service.QuestionTableService;
import com.zxd.www.global.entity.dto.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Makonike
 * @date 2021-10-20 18:42
 **/
@RestController
@RequestMapping("/exam/table")
public class QuestionTableController {

    @Autowired
    private QuestionTableService questionTableService;

    /**
     * 添加测验题目表
     * @param questionTableEntity 测验题目表
     */
    @PostMapping("/add")
    public JsonResponse add(@RequestBody QuestionTableEntity questionTableEntity){
        if (questionTableService.add(questionTableEntity)) {
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("题目表" + questionTableEntity.getTableName() + "已存在");
    }

    /**
     * 删除测验题目表
     * 这里暂时先不删掉已经选入的题目
     * @param tableId 题目表id
     */
    @DeleteMapping("/delete/{tableId}")
    public JsonResponse delete(@PathVariable("tableId")Integer tableId){
        if (questionTableService.delete(tableId)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("删除测验题目表失败!");
    }

    /**
     * 查看当前教师的测验题目表
     */
    @GetMapping("/list")
    public JsonResponse getList(){
        return new JsonResponse().data(questionTableService.getByTeacherId());
    }

    @PutMapping("/update")
    public JsonResponse update(@RequestBody QuestionTableEntity questionTableEntity){
        if (questionTableService.update(questionTableEntity)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("题目表" + questionTableEntity.getTableName() + "已存在");
    }

}
