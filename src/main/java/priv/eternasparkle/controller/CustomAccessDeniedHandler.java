package priv.eternasparkle.controller;

import priv.eternasparkle.util.R;
import priv.eternasparkle.util.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 自定义拒绝访问处理程序
 *
 * @author EternaSparkle
 * @version 1.0.0
 * @date 2024/09/24
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * 处理
     *
     * @param req  要求
     * @param resp 回复
     * @param e    e
     * @throws IOException      io异常
     * @throws ServletException Servlet 异常
     */
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
        System.out.println("\n+---------------------------------+");
        System.out.println("|      403!403!403!403!           |");
        System.out.println("+---------------------------------+\n");
        R r = R.err(R.MESSAGE_403,R.UNAUTHORIZED);
        WebUtils.write(r,resp);
    }
}
