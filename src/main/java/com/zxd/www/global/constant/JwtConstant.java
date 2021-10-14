package com.zxd.www.global.constant;

import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Makonike
 * @date 2021-09-21 14:34
 **/
public class JwtConstant {

    /**
     * 加密算法
     */
    public final static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    /**
     * 私钥
     */
    public final static String PERSONAL_SECRET = "anyView-exam-secret-key";

    /**
     * 过期时间 - 24h
     */
    public final static Long ACCESS_TOKEN_EXPIRATION = 3600 * 1000 * 24L;

    /**
     * jwt签发者
     */
    public final static String JWT_ISS = "anyView-exam";

    /**
     * 所有人
     */
    public final static String USER_SUBJECT = "user";

    /**
     * 所有人
     */
    public final static String ADMIN_SUBJECT = "admin";

    /**
     * token前缀
     */
    public final static String TOKEN_PREFIX = "token-";

    public final static String ALG_KEY = "alg";

    public final static String ALG_VALUE = "HS256";

    public final static String TYP_KEY = "typ";

    public final static String TYP_VALUE = "JWT";

    public final static String CLAIMS_USER_ID_KEY = "userId";

    public final static String CLAIMS_ADMIN_ID_KEY = "adminId";

//    public final static String CLAIMS_USERNAME_KEY = "userName";

    public final static String CLAIMS_ISS_KEY = "iss";

    public final static String USER_TOKEN_HEADER_KEY = "User-Token";

    public final static String ADMIN_TOKEN_HEADER_KEY = "Admin-Token";
}
