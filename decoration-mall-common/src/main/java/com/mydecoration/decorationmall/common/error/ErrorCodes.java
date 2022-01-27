package com.mydecoration.decorationmall.common.error;

/**
 * @author chaibing
 * @Description
 * @create 2022/1/26 15:17
 */
public class ErrorCodes {

    public final static Integer DB_INSERT_FAILED = 1101;
    public final static Integer DB_UPDATE_FAILED = 1102;

    public final static Integer GET_LOCK_FAILED = 1150;

    public final static Integer CREATE_VERSION_FAILED = 1210;
    public final static Integer PUBLISH_DEPLOYMENT_FAILED = 1211;

    public final static Integer CREATE_PRODUCT_FAILED = 1220;
    public final static Integer CREATE_DEVICE_FAILED = 1221;
    public final static Integer CREATE_MODULE_FAILED = 1222;
    public final static Integer CREATE_DEPLOYMENT_FAILED = 1223;

    public final static Integer UPDATE_DEPLOYMENT_FAILED = 1224;
    public final static Integer SORT_GROUP_VERSION_FAILED = 1225;
    public final static Integer REMOVE_GROUP_VERSION_FAILED = 1235;





    public final static Integer PARAM_INVALID = 1501;
    public final static Integer MATCH_DEPLOYMENT_FAILED = 1502;
    public final static Integer MATCH_GROUP_FAILED = 1503;
    public final static Integer MATCH_VERSION_FAILED = 1504;
    public final static Integer PACK_RESULT_FAILED = 1504;
    public final static Integer MATCH_LOG_FAILED = 1505;



    public final static Integer SYS_ERROR = 9099;
}
