package com.example.covid19.common.lang;

import lombok.Data;

@Data
public class Result {

    private int code;
    private String message;
    private Object data;

    public static Result success(Object data) {
        return success(200, "OK", data);
    }

    public static Result success(int code, String message, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result fail(int code, String message, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
