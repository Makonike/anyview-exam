package com.zxd.www.exam.controller;

import com.zxd.www.exam.entity.QuestionBand;
import com.zxd.www.exam.service.QuestionBandService;
import com.zxd.www.global.entity.dto.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * //TODO : 鉴权、管理员才有操作题库的权限
 * @author Makonike
 * @date 2021-10-20 20:05
 **/
@RestController
@RequestMapping("/exam/band")
public class QuestionBandController {

    @Autowired
    private QuestionBandService bandService;

    @PostMapping("/add")
    public JsonResponse add(@RequestBody QuestionBand questionBand){
        if (bandService.add(questionBand)) {
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("添加题库失败!");
    }

    @DeleteMapping("/delete/{bandId}")
    public JsonResponse delete(@PathVariable("bandId") Integer bandId){
        if (bandService.delete(bandId)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("删除题库失败！");
    }

    @GetMapping("/list")
    public JsonResponse list(){
        return new JsonResponse().data(bandService.getList());
    }

    @PutMapping("/update")
    public JsonResponse update(@RequestBody QuestionBand questionBand){
        if (bandService.updateName(questionBand)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("更新题库名失败！");
    }

}
