package priv.eternasparkle.util;

import java.util.HashMap;

public class R extends HashMap {

    //1.未认证的错误码
    public static final int UNAUTHENTICATED = 401;
    //2.权限不足的错误码
    public static final int UNAUTHORIZED    = 403;
    //3.资源不存在错误码
    public static final int RESOURCE_NOT_FOUND  = 404;
    //5.服务器内部错误的错误码
    public static final int SERVER_ERROR    = 500;
    public static final String MESSAGE_401 = "用户未认证";
    public static final String MESSAGE_403 = "权限不存在";

    public static R ok(){
        R r = new R();
        r.put("result","success");
        r.put("status",200);
        return r;
    }

    public static R err(
            Exception e, int status) {
        R r = new R();
        r.put("result","failed");
        r.put("status",status);
        r.put("cause",e.getCause());
        return r;
    }

    public static R err(
            String msg, int status) {
        R r = new R();
        r.put("result", "failed");
        r.put("status", status);
        r.put("cause",msg);
        return r;
    }

    public R put(String key, Object val){
        super.put( key, val );
        return this;
    }

    public static R ok(Object obj){
        R r = new R();
        r.put("result","success");
        r.put("status",200);
        r.put("data",obj);
        return r;
    }

    public static R err(Exception e) {
        R r = new R();
        r.put("result","failed");
        r.put("status",SERVER_ERROR);
        r.put("cause",e.getMessage());
        return r;
    }

}

