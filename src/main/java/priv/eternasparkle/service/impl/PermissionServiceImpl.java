package priv.eternasparkle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.eternasparkle.entity.Permission;
import priv.eternasparkle.mapper.PermissionMapper;
import priv.eternasparkle.service.PermissionService;

import java.util.List;

/**
 * @author EternaSparkle
 * @Date 2024/09/11
 * @Time 11:22
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {


    private final PermissionMapper permissionMapper;

    public PermissionServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }


    @Override
    public List<String> getPermissions(Integer roleId) {
        return permissionMapper.getPermissions(roleId);
    }

    @Override
    public List<String> getRoutePermissions(Integer roleId) {
        return permissionMapper.getRoutePermissions(roleId);
    }
}
