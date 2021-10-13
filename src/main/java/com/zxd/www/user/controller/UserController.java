package com.zxd.www.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zxd.www.global.constant.JwtConstant;
import com.zxd.www.global.constant.RedisConstant;
import com.zxd.www.global.entity.dto.JsonResponse;
import com.zxd.www.global.enums.ResultCode;
import com.zxd.www.global.util.JwtUtil;
import com.zxd.www.global.util.RedisUtil;
import com.zxd.www.user.entity.UserEntity;
import com.zxd.www.user.service.UserService;
import org.apache.shiro.SecurityUtils;
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
        // TODO: 根据id查询stu信息然后返回
        return new JsonResponse().message("用户信息").data(userEntity);
    }


}
