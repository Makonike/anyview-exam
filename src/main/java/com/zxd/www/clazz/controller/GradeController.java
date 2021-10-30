package com.zxd.www.clazz.controller;

import com.zxd.www.clazz.service.GradeService;
import com.zxd.www.global.entity.dto.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Makonike
 * @date 2021-10-30 14:01
 **/
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("/get/list")
    public JsonResponse list(){
        return new JsonResponse().data(gradeService.getList());
    }


}
