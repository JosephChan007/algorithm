package org.joseph.algorithm.common;

public class Result<T> {
    public static final int CODE_UNLOGIN = -1;
    public static final int CODE_ERROR = 500;
    public static final int CODE_OK = 200;


    public static final String MSG_UNLOGIN = "未登录";
    public static final String MSG_ERROR = "失败";
    public static final String MSG_OK = "成功";

    public Integer code;
    public String msg;
    public T data;

    public Result() {
        this.code = CODE_ERROR;
        this.msg = MSG_ERROR;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result<T> response(Integer code, String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result response(Integer code, String msg) {
        return response(code, msg, null);
    }

    public static <T> Result<T> success(T data) {
        return response(CODE_OK, MSG_OK, data);
    }

    public static Result unlogin() {
        return Result.response(Result.CODE_UNLOGIN, Result.MSG_UNLOGIN);
    }

    public static Result failed(Integer code, String msg) {
        return response(code, msg, null);
    }

    public static Result failed(String msg) {
        return response(CODE_ERROR, msg, null);
    }

    public static Result failed() {
        return response(CODE_ERROR, MSG_ERROR, null);
    }
}
