package com.wuye.wuyehoutai2.api;


/**
 * 返回给前端的json格式工具类
 */
public class ResultApiUtil {

    public static ResultApi error(ResultEnum resultEnum) {
        ResultApi resultApi = new ResultApi();
        resultApi.setCode(resultEnum.getCode());
        resultApi.setMsg(resultEnum.getMsg());
        resultApi.setData(null);
        return resultApi;
    }

    public static ResultApi error(String msg) {
        ResultApi resultApi = new ResultApi();
        resultApi.setCode(ResultEnum.PARAM_ERROR.getCode());
        resultApi.setMsg(msg);
        resultApi.setData(null);
        return resultApi;
    }

    public static ResultApi success(Object object) {
        ResultApi success = success(ResultEnum.RESULT_OK, object);
        return success;
    }

    public static ResultApi success(ResultEnum resultEnum, Object object) {
        ResultApi resultApi = new ResultApi();
        resultApi.setCode(resultEnum.getCode());
        resultApi.setMsg(resultEnum.getMsg());
        resultApi.setData(object);
        return resultApi;
    }
}
