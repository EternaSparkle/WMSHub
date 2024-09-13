package priv.eternasparkle.templates;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import priv.eternasparkle.mapper.MenuMapper;
import priv.eternasparkle.mapper.PermissionMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author EternaSparkle
 * @Date 2024/09/13
 * @Time 9:53
 */

@Component("menuTemplate")
public class MenuTemplate {
    @Resource(name="myJacksonTemp")
    private RedisTemplate<String,Object> redisTemplate;

    public MenuTemplate(PermissionMapper permissionMapper, MenuMapper menuMapper) {
        this.permissionMapper = permissionMapper;
        this.menuMapper = menuMapper;
    }

    private final PermissionMapper permissionMapper;
    private final MenuMapper menuMapper;

    public List getMenusByRole(Integer roleId) {
        // 获取Redis操作对象
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();

        // 构建Redis的key
        String KEY = "MENU:" + roleId;

        // 尝试从Redis中获取菜单列表
        List menus = (List) ops.get(KEY);

        // 如果菜单列表为空，则从数据库中获取
        if (menus == null) {
            menus = menuMapper.getMenuByRole(roleId);
            // 将获取到的菜单列表存入Redis缓存
            ops.set(KEY, menus);
        }

        // 返回菜单列表
        return menus;
    }
}
