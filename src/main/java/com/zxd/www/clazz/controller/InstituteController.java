package com.zxd.www.clazz.controller;

import com.zxd.www.clazz.entity.InstituteEntity;
import com.zxd.www.clazz.service.InstituteService;
import com.zxd.www.global.entity.dto.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Makonike
 * @date 2021-10-29 18:26
 **/
@RestController
@RequestMapping("/institute")
public class InstituteController {

    @Autowired
    private InstituteService instituteService;


    @PostMapping("/save")
    public JsonResponse add(@RequestBody InstituteEntity instituteEntity){
        if (instituteService.add(instituteEntity)) {
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("新增学院失败！");
    }

    @DeleteMapping("/delete/{instituteId}")
    public JsonResponse delete(@PathVariable("instituteId") Integer instituteId){
        if (instituteService.delete(instituteId)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("未找到该学院");
    }

    @PutMapping("/update")
    public JsonResponse update(@RequestBody InstituteEntity instituteEntity){
        if (instituteService.update(instituteEntity)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("更新失败！");
    }

    @GetMapping("/get/{instituteId}")
    public JsonResponse getById(@PathVariable("instituteId") Integer instituteId){
        return new JsonResponse().data(instituteService.getById(instituteId));
    }

    @GetMapping("/get/list")
    public JsonResponse getList(){
        return new JsonResponse().data(instituteService.getList());
    }
}
