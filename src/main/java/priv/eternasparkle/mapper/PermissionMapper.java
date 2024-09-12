package priv.eternasparkle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Param;
import priv.eternasparkle.entity.Permission;

import java.util.List;

public interface PermissionMapper
        extends BaseMapper<Permission> {
    List<String> getPermissions(
            @Param("roleId")Integer roleId);

    List<String> getRoutePermissions(
            @Param("roleId")Integer roleId);
}