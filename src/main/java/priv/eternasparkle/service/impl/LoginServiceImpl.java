package priv.eternasparkle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import priv.eternasparkle.DTO.UserDetailDTO;
import priv.eternasparkle.controller.CustomAuthenticationEntryPoint;
import priv.eternasparkle.entity.Permission;
import priv.eternasparkle.entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import priv.eternasparkle.mapper.PermissionMapper;
import priv.eternasparkle.service.LoginService;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource(name = "myJacksonTemp")
    private RedisTemplate<String,Object> redisTemplate;
    @Resource
    private AuthenticationManager authcManager;

    private final PermissionMapper permissionMapper;

    public LoginServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public User login(String identity, String credential, HttpServletResponse response){
        Authentication token =new UsernamePasswordAuthenticationToken(identity,credential);
        Authentication authc =authcManager.authenticate(token);
        UserDetailsImpl details =(UserDetailsImpl)authc.getPrincipal();
        UserDetailDTO DTO = details.getUserDetailsDTO();
        Integer roleId = DTO.getUser().getRoleId();
        if (DTO.getUser().getRoleId()==null || Objects.equals(DTO.getUser().getUsername(), "admin")){
            roleId = 0;
        }
        List<String> permissions = permissionMapper.getPermissions(roleId);
        for (String permission : permissions){
            DTO.addPermission(permission);
        }
        System.out.println("555555555555555551111111");
        System.out.println(DTO);
        User user = DTO.getUser();
        String KEY = "USER:"+user.getId()+":"+user.getPrimaryRoleId();
        ValueOperations<String,Object> OP = redisTemplate.opsForValue();
        OP.set(KEY,DTO,60*60, TimeUnit.SECONDS);
        return user;
    }

}
