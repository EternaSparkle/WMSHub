package priv.eternasparkle.filter;

import io.jsonwebtoken.Claims;
import priv.eternasparkle.service.impl.UserDetailsImpl;
import priv.eternasparkle.DTO.UserDetailDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import priv.eternasparkle.util.JwtUtils;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource(name="myJacksonTemp")
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String token = req.getHeader("token");
        if(StringUtils.isEmpty(token)){
            chain.doFilter(req,resp);
            return;
        }
        Claims claims = JwtUtils.parseJWT(token);

        Integer userId = (Integer)claims.get("userId");
        Integer roleId = (Integer)claims.get("roleId");
        String KEY = "USER:" + userId +":"+ roleId;
        System.out.println("+---------------------------------+");
        System.out.println("我拿到键" + KEY);
        System.out.println("+---------------------------------+\n");
        ValueOperations<String,Object> OP = redisTemplate.opsForValue();
        UserDetailDTO DTO = (UserDetailDTO)OP.get(KEY);
        UserDetailsImpl userDetails = new UserDetailsImpl(DTO);
        System.out.println("+---------------------------------+");
        System.out.println("我拿到用户权限" + userDetails.getAuthorities());
        System.out.println("+---------------------------------+\n");
        Authentication usernameToken = new UsernamePasswordAuthenticationToken(
                userDetails,null,
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication( usernameToken );
        chain.doFilter(req,resp);
    }
}
