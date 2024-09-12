package priv.eternasparkle.util;

import io.jsonwebtoken.Claims;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import priv.eternasparkle.DTO.UserDetailDTO;
import priv.eternasparkle.entity.User;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component(value="tokenTemplate")
public class TokenTemplate {
    @Resource(name="myJacksonTemp")
    private RedisTemplate<String,Object> redisTemplate;

    private String JwtToKEY( String token ){
        Claims claims = JwtUtils.parseJWT(token);
        Integer userId = (Integer)claims.get("userId");
        Integer roleId = (Integer)claims.get("roleId");
        return  "USER:"+ userId +":"+ roleId;
    }
    private String UserToKEY( User user ){
        Integer userId = user.getId();
        Integer roleId = user.getPrimaryRoleId();
        return "USER:"+ userId +":"+ roleId;
    }

    public Object getValue(String token, String KEY){
        Claims claims = JwtUtils.parseJWT(token);
        return claims.get( KEY );
    }

    public UserDetailDTO getUserDetailsFromCache(String token){
        //1.转换 JWT ==> KEY.
        String KEY = JwtToKEY( token );
        //2.从 Redis 中取出 UserDetailsDTO.
        ValueOperations<String, Object> OP = redisTemplate.opsForValue();
        UserDetailDTO DTO = (UserDetailDTO)OP.get(KEY);
        return DTO;
    }

    public UserDetailDTO setUserDetailsToCache( UserDetailDTO DTO ){
        //1.转换 User To KEY.
        String KEY = UserToKEY( DTO.getUser() );
        //2.设置 UserDetailsDTO 到 REDIS.
        ValueOperations<String, Object> OP = redisTemplate.opsForValue();
        OP.set(KEY, DTO, 60*10, TimeUnit.SECONDS);
        return DTO;
    }

    public void expire(UserDetailDTO dto, int seconds) {
        String KEY = UserToKEY( dto.getUser() );
        redisTemplate.expire( KEY, seconds, TimeUnit.SECONDS);
    }

    public boolean remove(String token) {
        String KEY = JwtToKEY( token );
        return redisTemplate.delete( KEY );
    }

}
