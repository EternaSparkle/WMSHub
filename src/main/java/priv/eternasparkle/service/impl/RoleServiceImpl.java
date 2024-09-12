package priv.eternasparkle.service.impl;

import org.springframework.stereotype.Service;

import priv.eternasparkle.entity.Role;
import priv.eternasparkle.mapper.RoleMapper;
import priv.eternasparkle.service.RoleService;


/**
 * @author EternaSparkle
 * @Date 2024/09/02
 * @Time 11:10
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }


}