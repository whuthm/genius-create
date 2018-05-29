package com.whuthm.gc.vo;

public class ApiResponse<DATA> {

    private final Response rsp;
    private final DATA data;

    public ApiResponse(Response rsp, DATA data) {
        this.rsp = rsp;
        this.data = data;
    }

    public ApiResponse(Response rsp) {
        this(rsp, null);
    }

    public Response getRsp() {
        return rsp;
    }

    public DATA getData() {
        return data;
    }

    public static <DATA> ApiResponse<DATA> success(DATA data) {
        return new ApiResponse<>(Response.SUCCESS, data);
    }

    public static <DATA> ApiResponse<DATA> success() {
        return new ApiResponse<>(Response.SUCCESS);
    }

    public static <DATA> ApiResponse<DATA> error(int code, String msg) {
        return new ApiResponse<>(new Response(code, msg), null);
    }

    public static <DATA> ApiResponse<DATA> error(ResponseStatus status) {
        return error(status.getCode(), status.getMsg());
    }

    public static class Response {

        public static final Response SUCCESS = new Response(0, "ok");

        private final int code;
        private final String msg;

        public Response(int code, String msg) {
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

}
