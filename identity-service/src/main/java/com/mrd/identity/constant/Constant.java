package com.mrd.identity.constant;

/**
 * @author ducnh
 */
public class Constant {

    public static final String SRV_VER = "/v1.0";

    public static final int USER_SIGNUP_NORMAL = 0;
    public static final int USER_SIGNUP_FACEBOOK = 1;
    public static final int USER_SIGNUP_GOOGLE = 2;
    public static final int USER_SIGNUP_APPLE = 3;

    // Validation message
    public static final String VALIDATION_INVALID_PARAM_VALUE = "Invalid param value";
    public static final String VALIDATION_DATA_NOT_FOUND = "Data not found";
    public static final String VALIDATION_ACCOUNT_LOCKED = "Tài khoản đang bị khóa";
    public static final String VALIDATION_ACCOUNT_NOT_LOGIN = "Bạn chưa đăng nhập";

    // Response messages
    public static final String RESPONSE_UNKNOW_ERR = "Lỗi không xác định";

}
