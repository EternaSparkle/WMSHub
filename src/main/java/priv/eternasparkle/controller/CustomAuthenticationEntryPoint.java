package priv.eternasparkle.controller;

import priv.eternasparkle.util.R;
import priv.eternasparkle.util.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义身份验证入口点
 *
 * @author EternaSparkle
 * @version 1.0.0
 * @date 2024/09/24
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * 开始
     *
     * @param req           要求
     * @param resp          回复
     * @param authException auth 异常
     * @throws IOException      io异常
     * @throws ServletException Servlet 异常
     */
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("\n+---------------------------------+");
        System.out.println("|      401!401!401!401!           |");
        System.out.println("+---------------------------------+\n");
        R r = R.err(R.MESSAGE_401,R.UNAUTHENTICATED);
        WebUtils.write(r,resp);
    }

}
