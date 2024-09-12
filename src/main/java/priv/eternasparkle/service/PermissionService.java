package priv.eternasparkle.service;


import com.baomidou.mybatisplus.extension.service.IService;
import priv.eternasparkle.entity.Permission;

import java.util.List;

/**
 * @author EternaSparkle
 * @Date 2024/09/02
 * @Time 11:09
 */
public interface PermissionService extends IService<Permission> {
    List<String> getPermissions(Integer roleId);

    List<String> getRoutePermissions(Integer roleId);
}
