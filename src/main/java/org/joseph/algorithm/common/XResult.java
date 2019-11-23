package org.joseph.algorithm.common;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class XResult<T> {

    public static final String CODE_ERROR = "500";
    public static final String CODE_OK = "200";
    public static final String CODE_DB_OK = "600";
    public static final String CODE_DB_ERROR = "601";
    public static final String CODE_DB_NULL = "602";


    public static final String MSG_ERROR = "失败";
    public static final String MSG_OK = "成功";
    public static final String MSG_DB_OK = "数据库操作成功";
    public static final String MSG_DB_ERROR = "数据库操作错误";
    public static final String MSG_DB_NULL = "数据库查询为空";

    public String code;
    public String msg;
    public T data;
    public Boolean success;

    public XResult() {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    public static <T> XResult<T> response(String code, String msg, Boolean success, T data) {
        XResult<T> result = new XResult<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setSuccess(success);
        result.setData(data);
        return result;
    }

    public static XResult response(String code, String msg, Boolean success) {
        return response(code, msg, success, null);
    }

    public static <T> XResult<T> success(T data) {
        return response(CODE_OK, MSG_OK, Boolean.TRUE, data);
    }

    public static <T> XResult<T> success(String code, String msg, T data) {
        return response(code, msg, Boolean.TRUE, data);
    }

    public static XResult failed(String code, String msg) {
        return response(code, msg, Boolean.FALSE);
    }

    public static XResult failed(String msg) {
        return response(CODE_ERROR, msg, Boolean.FALSE);
    }

    public static <T> XResult<T> failed(String code, String msg, T data) {
        return response(code, msg, Boolean.FALSE, data);
    }

    public static XResult failed() {
        return response(CODE_ERROR, MSG_ERROR, Boolean.FALSE);
    }

    public static <T> XResult<T> failed(T data) {
        return response(CODE_ERROR, MSG_ERROR, Boolean.FALSE, data);
    }

    public static <T> XResult<T> successForDb(T data) {
        return response(CODE_DB_OK, MSG_DB_OK, Boolean.TRUE, data);
    }

    public static XResult failedForDbNull() {
        return response(CODE_DB_NULL, MSG_DB_NULL, Boolean.FALSE);
    }

    public static XResult failedForDbError() {
        return response(CODE_DB_ERROR, MSG_DB_ERROR, Boolean.FALSE);
    }

    public Result toResult() {
        if (this.getSuccess()) {
            return Result.success(this.getData());
        }
        return Result.response(Integer.valueOf(this.getCode()), this.getMsg(), this.getData());
    }

    /**
     * 查询成功且有数据
     */
    public boolean isPresent() {
        if (this.getData() instanceof List) {
            return this.getSuccess() && CollectionUtils.isNotEmpty((List) this.getData());
        }
        return this.getSuccess() && null != this.getData();
    }

    /**
     * 无数据/查询失败
     */
    public boolean isAbsent() {
        return !this.isPresent();
    }

}
