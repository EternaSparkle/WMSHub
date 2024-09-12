package priv.eternasparkle.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import priv.eternasparkle.DTO.UserDetailDTO;
import priv.eternasparkle.entity.Permission;
import priv.eternasparkle.entity.Role;
import priv.eternasparkle.entity.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import priv.eternasparkle.mapper.PermissionMapper;
import priv.eternasparkle.mapper.RoleMapper;
import priv.eternasparkle.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;
    private final PermissionMapper permissionMapper;

    public UserDetailsServiceImpl(UserMapper userMapper, PermissionMapper permissionMapper) {
        this.userMapper = userMapper;

        this.permissionMapper = permissionMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =null;
        user = userMapper.selectUserAndRoleByUserName(username);
        System.out.println("11111111111111111111111111111111111111111111111111111111111");
        System.out.println(user);
        Role role = user.getRole();
        List<String> perms = new ArrayList<>();
        System.out.println("333333333333333333333333333333");
        if(user ==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        try {
            System.out.println("444444444444444444444444444444");
            perms = permissionMapper.getPermissions(role.getId());
            System.out.println("55555555555555555555555555555555");
            System.out.println(perms);
            System.out.println("55555555555555555555555555555555");
        }catch (Exception e){
            user.getPrimaryRole();
        }finally {
            UserDetailDTO DTO =new UserDetailDTO(user,perms);
            UserDetails userDetails = new UserDetailsImpl(DTO);
            return userDetails;
        }
    }

}
