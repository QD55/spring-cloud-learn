package com.example.common;

import lombok.Data;

@Data
public class R {
    private int code;
    private String msg;
    private Object data;

    public static R ok() {
        R r = new R();
        r.setCode(200);
        r.setMsg("ok");
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(500);
        r.setMsg("error");
        return r;
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}
