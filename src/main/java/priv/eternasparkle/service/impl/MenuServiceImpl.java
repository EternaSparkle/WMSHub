package priv.eternasparkle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.eternasparkle.entity.Menu;
import priv.eternasparkle.mapper.MenuMapper;
import priv.eternasparkle.service.MenuService;

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

    @Override
    public List<Menu> getMenuByRole(Integer roleId) {
        return menuMapper.getMenuByRole(roleId);
    }
}
