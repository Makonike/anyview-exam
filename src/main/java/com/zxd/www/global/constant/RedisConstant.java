package com.zxd.www.global.constant;

/**
 * @author Makonike
 * @date 2021-10-12 22:23
 **/
public class RedisConstant {

    public static final String PREFIX_USER_INFO = "USER_INFO_";

    public static final String PREFIX_ADMIN_INFO = "ADMIN_INFO_";

    public static final String PREFIX_EXAM_SETUP = "EXAM_SETUP_";

    public static final String PREFIX_EXAM_START = "EXAM_START_";

    public static final String PREFIX_EXAM_STOP = "EXAM_STOP_";

    /**
     * 设置锁的时间为2s
     */
    public static final long LOCK_KEY_TIME = 2L;

    /**
     * 定时任务锁
     */
    public static final long LOCK_SCHEDULED_TIME = 5L;


}
