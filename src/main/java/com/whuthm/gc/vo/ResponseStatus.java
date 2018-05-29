package com.whuthm.gc.vo;

public enum  ResponseStatus {

    SUCCESS(0, "OK"),
    ILLEGAL_ARGUMENTS(101, "Illegal Arguments");

    private final int code;
    private final String msg;

    ResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
