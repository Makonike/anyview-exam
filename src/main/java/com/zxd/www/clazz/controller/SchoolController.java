package com.zxd.www.clazz.controller;

import com.zxd.www.clazz.entity.SchoolEntity;
import com.zxd.www.clazz.service.SchoolService;
import com.zxd.www.global.entity.dto.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Makonike
 * @date 2021-10-29 17:57
 **/
@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @PostMapping("/save")
    public JsonResponse add(@RequestBody SchoolEntity schoolEntity){
        if (schoolService.add(schoolEntity)) {
            return new JsonResponse();
        }
        return new JsonResponse().unauthorized().message("新增学校！");
    }

    @DeleteMapping("/delete/{schoolId}")
    public JsonResponse delete(@PathVariable("schoolId") Integer schoolId){
        if (schoolService.delete(schoolId)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("找不到该学校！");
    }

    @GetMapping("/get/{schoolId}")
    public JsonResponse getById(@PathVariable("schoolId") Integer schoolId){
        return new JsonResponse().data(schoolService.getById(schoolId));
    }

    @GetMapping("/get/list")
    public JsonResponse getList(){
        return new JsonResponse().data(schoolService.getList());
    }

    @PutMapping("/update")
    public JsonResponse update(@RequestBody SchoolEntity schoolEntity){
        if (schoolService.update(schoolEntity)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("找不到该学校");
    }

}
