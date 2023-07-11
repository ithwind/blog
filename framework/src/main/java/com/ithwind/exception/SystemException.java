package com.ithwind.exception;

import com.ithwind.api.ResultCode;

public class SystemException extends RuntimeException{
    private long code;
    private String msg;

    public void setCode(long code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
    }
}
