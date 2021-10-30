package com.zxd.www.user.util;

import com.zxd.www.global.constant.JwtConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author Makonike
 * @date 2021-10-12 15:25
 **/
@Slf4j
public class JwtUtil {

    public static String createToken(Integer userId){

        // 载荷 map / Jwt的载荷，第二部分
        Map<String,Object> claims = new HashMap<>(2);

        // 私有声明 / 自定义数据，根据业务需要添加
        claims.put(JwtConstant.CLAIMS_USER_ID_KEY, userId);
        ///
//        claims.put(JwtConstant.CLAIMS_USERNAME_KEY, user.getUsername());

        // 可不设置 默认格式是{"alg":"HS256"}
        Map<String, Object> map = new HashMap<>(2);
        map.put(JwtConstant.ALG_KEY, JwtConstant.ALG_VALUE);
        map.put(JwtConstant.TYP_KEY, JwtConstant.TYP_VALUE);

        // 自定义标准声明
        claims.put(JwtConstant.CLAIMS_ISS_KEY, JwtConstant.JWT_ISS);
            /*	iss: jwt签发者
                sub: jwt所面向的用户
                aud: 接收jwt的一方
                exp: jwt的过期时间，这个过期时间必须要大于签发时间
                nbf: 定义在什么时间之前，该jwt都是不可用的.
                iat: jwt的签发时间
                jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
            */

        // 设置jwt body
        return Jwts.builder()
                // header
                .setHeader(map)
                // claims
                .setClaims(claims)
                // jwt-id
                .setId(UUID.randomUUID().toString())
                // 签发时间
                .setIssuedAt(new Date())
                // exp time
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstant.ACCESS_TOKEN_EXPIRATION))
                .setSubject(JwtConstant.USER_SUBJECT)
                // sign
                .signWith(JwtConstant.SIGNATURE_ALGORITHM, JwtConstant.PERSONAL_SECRET)
                .compact();
    }

    /**
     * 校验token, 返回claims
     * @param token token
     * @return claims，失败返回 null
     */
    public static Claims parseToken(String token){
        Claims claims = null;
        try {
             claims = Jwts.parser()
                    .setSigningKey(JwtConstant.PERSONAL_SECRET)
                    // 前端返回时 请求头中key:User-Token，value:token-[TOKEN]
                    .parseClaimsJws(token.replace(JwtConstant.TOKEN_PREFIX, ""))
                    .getBody();
             return claims;
        } catch (ExpiredJwtException e){
            log.info("--- token已失效，请重新登录！ ---");
        } catch (Exception e){
            log.info("--- token解析异常，认证失败！ ---");
        }
        return null;
    }

    /**
     * 校验token
     * @param token token
     * @return 用户id，失败返回0
     */
    public static Integer verifyToken(String token){
        Optional<Claims> claims = Optional.ofNullable(parseToken(token));
        if(claims.isPresent()){
            return (Integer) claims.get().get(JwtConstant.CLAIMS_USER_ID_KEY);
        }
        return 0;
    }

    /**
     * 校验token并获取用户Id
     * @param token token
     */
    public static Integer getUserId(String token){
        Optional<Integer> userId = Optional.ofNullable(verifyToken(token));
        if(userId.isPresent() && userId.get() != 0){
            return userId.get();
        }
        return null;
    }



}
