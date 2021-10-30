package com.zxd.www.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zxd.www.global.constant.JwtConstant;
import com.zxd.www.global.constant.RedisConstant;
import com.zxd.www.global.entity.dto.JsonResponse;
import com.zxd.www.global.enums.ResultCode;
import com.zxd.www.user.util.JwtUtil;
import com.zxd.www.global.util.RedisUtil;
import com.zxd.www.user.entity.UserEntity;
import com.zxd.www.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * userController
 * @author Makonike
 * @date 2021-10-12 14:38
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/login")
    public JsonResponse login(@RequestBody UserEntity loginUser){
        String username = loginUser.getUserName();
        String password = loginUser.getUserPassword();

        // 校验用户是否有效
        UserEntity userEntity = userService.getByUserName(username);
        if (userEntity == null){
            return new JsonResponse(ResultCode.UNAUTHORIZED, "用户不存在", null);
        }

        // 校验用户名或密码是否正确
        String userPassword = new Sha256Hash(password, userEntity.getSalt()).toHex();
        if(!userEntity.getUserPassword().equals(userPassword)){
            return new JsonResponse(ResultCode.UNAUTHORIZED, "用户名或密码错误", null);
        }
        log.info("用户:" + loginUser.getUserName() + "登录成功!");
        // 用户登录信息
        return userInfo(userEntity);
    }


    /**
     * 封装用户信息并缓存到redis中
     * @param userEntity userEntity
     */
    private JsonResponse userInfo(UserEntity userEntity){
        Integer userId = userEntity.getUserId();

        // 生成token
        String token = JwtUtil.createToken(userId);

        userEntity.setSalt(null);
        userEntity.setUserPassword(null);
        // 缓存用户信息
        redisUtil.set(RedisConstant.PREFIX_USER_INFO + userEntity.getUserId(), JSON.toJSONString(userEntity), JwtConstant.ACCESS_TOKEN_EXPIRATION / 1000);

        JSONObject object = new JSONObject();
        object.put("token", token);

        return new JsonResponse(ResultCode.OK, "登录成功", object);
    }

    /**
     * 返回未授权的信息
     * @param message message
     */
    @GetMapping("/unauthorized/{message}")
    public JsonResponse unauthorized(@PathVariable String message){
        return new JsonResponse().unauthorized().message(message);
    }

    /**
     * 查询当前登录用户的用户信息
     */
    @GetMapping("/info")
    public JsonResponse info(){
        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        Integer userId = userEntity.getUserId();
        return new JsonResponse().message("查询用户信息成功！").data(userService.getInfoById(userId));
    }

    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    public JsonResponse save(@RequestBody UserEntity userEntity){
        if(userService.save(userEntity)){
            return new JsonResponse();
        }
        return new JsonResponse().badRequest().message("保存失败");
    }

    @PutMapping("/update")
    @RequiresPermissions("sys:user:update")
    public JsonResponse update(@RequestBody UserEntity userEntity){
        if(userService.update(userEntity)){
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("更新失败，未找到该用户信息！");
    }

    @GetMapping("/id/{userId}")
    public JsonResponse getByUserId(@PathVariable("userId") Integer userId){
        return new JsonResponse().data(userService.getInfoById(userId));
    }

    /**
     * 获取所有用户列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    public JsonResponse list(){
        return new JsonResponse().data(userService.getList());
    }

    /**
     * 删除用户
     * @param userId 用户id
     */
    @DeleteMapping("/delete/{userId}")
    @RequiresPermissions("sys:user:delete")
    public JsonResponse delete(@PathVariable("userId") Integer userId){
        if (userService.deleteById(userId)) {
            return new JsonResponse();
        }
        return new JsonResponse().notFound().message("删除失败，未找到该用户！");
    }


}
