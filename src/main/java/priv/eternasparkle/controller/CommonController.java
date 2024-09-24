package priv.eternasparkle.controller;


import org.springframework.web.bind.annotation.*;
import priv.eternasparkle.DTO.LoginDTO;
import priv.eternasparkle.entity.Menu;
import priv.eternasparkle.service.MenuService;
import priv.eternasparkle.service.PermissionService;

import priv.eternasparkle.util.JwtUtils;
import priv.eternasparkle.entity.User;
import priv.eternasparkle.service.LoginService;
import priv.eternasparkle.util.R;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共控制器
 *
 * @author EternaSparkle
 * @version 1.0.0
 * @date 2024/09/24
 */
@RestController
@RequestMapping("/Common")
public class CommonController {
    /**
     * 登录服务
     */
    private final LoginService loginService;
    /**
     * 权限服务
     */
    private final PermissionService permissionService;
    /**
     * 菜单服务
     */
    private final MenuService menuService;

    /**
     * 公共控制器
     *
     * @param loginService      登录服务
     * @param permissionService 权限服务
     * @param menuService       菜单服务
     */
    public CommonController(LoginService loginService, PermissionService permissionService, MenuService menuService) {
        this.loginService = loginService;
        this.permissionService = permissionService;
        this.menuService = menuService;
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param response 响应
     * @return {@link R }
     */
    @RequestMapping("/login")
    public R login(String username, String password, HttpServletResponse response){
        User svUser = null;
        try {
            svUser =loginService.login(username,password,response);
            System.out.println("+------------------------+");
            System.out.println("+login方法被调用+");
            System.out.println(svUser);
            System.out.println("+------------------------+");
            Map map = new HashMap();
            map.put("userId",svUser.getId());
            map.put("roleId",svUser.getRoleId());
            svUser.setRoleId(svUser.getPrimaryRoleId());
            String jwtToken = JwtUtils.genarateJWT(map);
            return R.ok()
                    .put("userId",svUser.getId())
                    .put("roleId",svUser.getPrimaryRoleId()==-1?null:svUser.getPrimaryRoleId())
                    .put("token",jwtToken);
        }catch (Exception e){
            e.printStackTrace();
            return  R.err(e,500);
        }
    }

    /**
     * do login
     *
     * @param username 用户名
     * @param password 密码
     * @param response 响应
     * @return {@link R }
     */
    @RequestMapping("/doLogin")
    private R doLogin(String username, String password,HttpServletResponse response){
        User svUser = null;
        svUser = loginService.login(username, password,response);

        Map map = new HashMap();
        map.put("userId", svUser.getId());
        map.put("roleId", svUser.getPrimaryRoleId());
        String jwtToken = JwtUtils.genarateJWT(map);
        return R.ok()
                .put("userId", svUser.getId())
                .put("roleId", svUser.getPrimaryRoleId()==-1?0:svUser.getPrimaryRoleId())
                .put("token", jwtToken);
    }

    /**
     * 使用 body 登录
     *
     * @param dto  DTO
     * @param resp 回复
     * @return {@link R }
     */
    @RequestMapping("/loginWithBody")
    public R loginWithBody(@RequestBody LoginDTO dto, HttpServletResponse resp) {
        try {
            return doLogin(dto.getUsername(), dto.getPassword(),resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(409);
            return R.err("用户身份认证失败.", 409);
        }
    }

    /**
     * 烫发
     *
     * @param roleId 角色 ID
     * @return {@link R }
     */
    @RequestMapping("/getPerms/{roleId}")
    public R getPerms(@PathVariable("roleId") Integer roleId) {
        try {
            List<String> perms = permissionService.getRoutePermissions(roleId);
            return R.ok(perms);
        } catch (Exception e) {
            e.printStackTrace();
            return R.err(e, 500);
        }
    }

    /**
     * 获取 Perms2
     *
     * @param roleId 角色 ID
     * @return {@link R }
     */
    @RequestMapping("/getPerms2/{roleId}")
    public R getPerms2(@PathVariable("roleId") Integer roleId) {
        try {
            if (roleId == -1){
                roleId =0;
            }
            List<String> perms = null;
            perms =   permissionService.getRoutePermissions2(roleId);
            return R.ok(perms);
        } catch (Exception e) {
            e.printStackTrace();
            return R.err(e, 500);
        }
    }

    /**
     * 获取菜单
     *
     * @param roleId 角色 ID
     * @return {@link R }
     */
    @RequestMapping("/getMenus/{roleId}")
    public R getMenus(@PathVariable("roleId") Integer roleId) {
        try {
            List<Menu> menus = menuService.getMenuByRole(roleId);
            return R.ok(menus);
        } catch (Exception e) {
            e.printStackTrace();
            return R.err(e, 500);
        }
    }

    /**
     * 获取菜单2
     *
     * @param token 令 牌
     * @return {@link R }
     */
    @RequestMapping("/getMenus2")
    public R getMenus2(@RequestHeader("token")String token){
        try {
            List<Menu> menus = null;
            menus = menuService.getMenuByRole2(token);
            return R.ok(menus);
        } catch (Exception e) {
            e.printStackTrace();
            return R.err(e, 500);
        }
    }
}


