package com.shaffer.health.utils;

/**
 * @program: health
 * @Date: 2021/4/27 14:21
 * @Author: Shaffer
 * @Description:
 */
public class RedisMessageConstant {
    /**
     * 用于缓存体检预约时发送的验证码
     */
    static final String SENDTYPE_ORDER = "001";
    /**
     * 用于缓存手机号快速登录时发送的验证码
     */
    static final String SENDTYPE_LOGIN = "002";
    /**
     * 用于缓存找回密码时发送的验证码
     */
    static final String SENDTYPE_GETPWD = "003";
}
