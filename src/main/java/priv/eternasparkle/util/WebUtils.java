package priv.eternasparkle.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {
    public static void write(R r, HttpServletResponse resp)
        throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        Integer code = (Integer) r.get("status");
        resp.setStatus( code );
        String json = JSONObject.toJSONString(r);
        resp.getWriter().write( json );
    }
}
