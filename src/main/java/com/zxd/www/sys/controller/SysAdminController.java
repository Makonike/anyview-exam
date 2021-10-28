package com.zxd.www.sys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zxd.www.global.constant.CommonConstant;
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
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

        // 校验密码是否正确
        String loginPassword = new Sha256Hash(adminPassword, adminEntity.getSalt()).toHex();
        if(!adminEntity.getAdminPassword().equals(loginPassword)){
            return new JsonResponse(ResultCode.UNAUTHORIZED, "密码错误", null);
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

    @PostMapping("/save")
    @RequiresPermissions("sys:admin:save")
    public JsonResponse save(@RequestBody SysAdminEntity admin){
        if(adminService.save(admin)){
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("保存管理员信息失败！");
    }

    @DeleteMapping("/delete/{adminId}")
    @RequiresPermissions("sys:admin:delete")
    public JsonResponse delete(@PathVariable("adminId") Integer adminId){
        if(adminId.equals(CommonConstant.SUPER_ADMIN_ID)){
            return new JsonResponse().unauthorized().message("无法操作超级管理员!");
        }
        SysAdminEntity admin = (SysAdminEntity) SecurityUtils.getSubject().getPrincipal();
        if(admin.getAdminId().equals(adminId)){
            return new JsonResponse().unauthorized().message("无法删除自己！");
        }
        if(adminService.delete(adminId)){
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("删除失败，未找到该管理员信息!");
    }

    @GetMapping("/list")
    @RequiresPermissions("sys:admin:list")
    public JsonResponse list(){
        return new JsonResponse().data(adminService.list());
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
     * @return 管理员的教师信息
     */
    @GetMapping("/info")
    public JsonResponse info(){
        SysAdminEntity admin = (SysAdminEntity) SecurityUtils.getSubject().getPrincipal();
        return new JsonResponse().data(adminService.teacherInfo(admin.getAdminId()));
    }

    @PutMapping("/update")
    @RequiresPermissions("sys:admin:update")
    public JsonResponse update(@RequestBody SysAdminEntity admin){
        if(admin.getAdminId().equals(CommonConstant.SUPER_ADMIN_ID)){
            return new JsonResponse().unauthorized().message("无法操作超级管理员！");
        }
        if (adminService.update(admin)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("更新失败，未找到该管理员");
    }

}
