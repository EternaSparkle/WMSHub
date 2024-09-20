package priv.eternasparkle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.eternasparkle.entity.Menu;
import priv.eternasparkle.mapper.MenuMapper;
import priv.eternasparkle.service.MenuService;
import priv.eternasparkle.templates.DataTemplate;
import priv.eternasparkle.templates.MenuTemplate;
import priv.eternasparkle.templates.TokenTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author EternaSparkle
 * @Date 2024/09/11
 * @Time 13:32
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    private final MenuMapper menuMapper;

    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }


    @Resource(name="dataTemplate")
    private DataTemplate dataTemplate;
    @Resource(name="tokenTemplate")
    private TokenTemplate tokenTemplate;
    @Resource(name="menuTemplate")
    private MenuTemplate menuTemplate;

    @Override
    public List<Menu> getMenuByRole(Integer roleId) {
        return menuMapper.getMenuByRole(roleId);
    }

    @Override
    public List<Menu> getMenuByRole2(String token){
        // 从令牌中获取角色ID
        Integer roleId = (Integer) tokenTemplate.getValue(token, "roleId");
        if (roleId==-1) {
            roleId=0;
        }
        // 根据角色ID获取菜单列表
        List<Menu> menus = menuTemplate.getMenusByRole(roleId);
        // 获取控制台路由
        List<Menu> routes = dataTemplate.getConsoleRoutes();
        // 将控制台路由添加到菜单列表中
        menus.addAll(routes);
        // 返回合并后的菜单列表
        return menus;
    }


}
