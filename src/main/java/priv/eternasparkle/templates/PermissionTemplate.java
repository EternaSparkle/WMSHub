package priv.eternasparkle.templates;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import priv.eternasparkle.mapper.MenuMapper;
import priv.eternasparkle.mapper.PermissionMapper;

import javax.annotation.Resource;
import java.util.List;

@Component("permissionTemplate")
public class PermissionTemplate {
    @Resource(name="myJacksonTemp")
    private RedisTemplate<String,Object> redisTemplate;
    private final PermissionMapper permissionMapper;
    private final MenuMapper menuMapper;

    public PermissionTemplate(PermissionMapper permissionMapper, MenuMapper menuMapper) {
        this.permissionMapper = permissionMapper;
        this.menuMapper = menuMapper;
    }

    public List<String> getMenusByRole(Integer roleId){
        // 使用RedisTemplate的ValueOperations接口来操作字符串类型的值
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        // 构建Redis中的key，格式为"PERM:" + 角色ID
        String KEY = "PERM:" + roleId;

        // 尝试从Redis中获取权限列表
        List<String> perms = (List)ops.get(KEY);

        // 如果没有从Redis中获取到权限列表，则从数据库中查询
        if (perms == null) {
            perms = permissionMapper.getRoutePermissions(roleId);
            // 将查询到的权限列表存入Redis，以便下次直接从缓存中获取
            ops.set(KEY, perms);
        }

        // 返回权限列表
        return perms;
    }

}
