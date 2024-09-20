package priv.eternasparkle.service;

import com.baomidou.mybatisplus.extension.service.IService;
import priv.eternasparkle.entity.Menu;

import java.util.List;

/**
 * @author EternaSparkle
 * @Date 2024/09/11
 * @Time 11:53
 */
public interface MenuService extends IService<Menu> {
    List<Menu> getMenuByRole(Integer roleId);
    List<Menu> getMenuByRole2(String token);
}
