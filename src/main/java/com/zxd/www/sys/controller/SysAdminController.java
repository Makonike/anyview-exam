package com.zxd.www.sys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zxd.www.global.constant.JwtConstant;
import com.zxd.www.global.constant.RedisConstant;
import com.zxd.www.global.entity.dto.JsonResponse;
import com.zxd.www.global.enums.ResultCode;
import com.zxd.www.global.util.RedisUtil;
import com.zxd.www.sys.entity.SysAdminEntity;
import com.zxd.www.sys.service.ShiroService;
import com.zxd.www.sys.service.SysAdminService;
import com.zxd.www.sys.util.AdminJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Makonike
 * @date 2021-10-14 18:38
 **/
@RestController
@RequestMapping("/sys")
@Slf4j
public class SysAdminController {

    @Autowired
    private SysAdminService adminService;

    @Autowired
    private ShiroService shiroService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/login")
    public JsonResponse login(@RequestBody SysAdminEntity admin){
        String adminName = admin.getAdminName();
        String adminPassword = admin.getAdminPassword();

        // 检验用户是否有效
        SysAdminEntity adminEntity = shiroService.getByName(adminName);
        if(adminEntity == null){
            return new JsonResponse(ResultCode.UNAUTHORIZED, "用户不存在", null);
        }

        // 校验用户名或密码是否正确
        String loginPassword = new Sha256Hash(adminPassword, adminEntity.getSalt()).toHex();
        if(!adminEntity.getAdminPassword().equals(loginPassword)){
            return new JsonResponse(ResultCode.UNAUTHORIZED, "用户名或密码错误", null);
        }

        log.info("管理员:" + admin.getAdminName() + "登录成功！");
        return adminInfo(adminEntity);
    }

    /**
     * 将用户信息存到redis中，并返回token
     * @param adminEntity admin
     */
    private JsonResponse adminInfo(SysAdminEntity adminEntity){
        Integer adminId = adminEntity.getAdminId();

        // 生成token
        String token = AdminJwtUtil.createToken(adminId);
        adminEntity.setSalt(null);
        adminEntity.setAdminPassword(null);

        redisUtil.set(RedisConstant.PREFIX_ADMIN_INFO + adminId, JSON.toJSONString(adminEntity), JwtConstant.ACCESS_TOKEN_EXPIRATION);

        JSONObject obj = new JSONObject();
        obj.put("token", token);
        return new JsonResponse().message("登录成功！").data(obj);
    }

    /**
     * 返回未授权信息
     * @param message 未授权信息
     */
    @GetMapping("/unauthorized/{message}")
    public JsonResponse unauthorized(@PathVariable String message){
        return new JsonResponse().message(message).unauthorized();
    }

    /**
     * TODO: 用来测试权限
     * @return
     */
    @GetMapping("/info")
    public JsonResponse info(){
        SysAdminEntity admin = (SysAdminEntity) SecurityUtils.getSubject().getPrincipal();
        return new JsonResponse().data(adminService.getById(admin.getAdminId()));
    }

}
