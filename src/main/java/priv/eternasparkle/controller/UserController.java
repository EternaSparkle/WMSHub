package priv.eternasparkle.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import priv.eternasparkle.entity.User;
import priv.eternasparkle.service.UserService;
import priv.eternasparkle.util.R;
import priv.eternasparkle.vo.UserInfoVO;
import priv.eternasparkle.vo.UserSearchVO;
import priv.eternasparkle.vo.UserVO;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户控制器
 *
 * @author EternaSparkle
 * @version 1.0.0
 * @date 2024/09/24
 */
@RestController
@RequestMapping("/System/User")
public class UserController {

    /**
     * 用户服务
     */
    private final UserService userService;

    /**
     * 用户控制器
     *
     * @param userService 用户服务
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 异常处理程序
     *
     * @param e    e
     * @param resp 回复
     * @return {@link R }
     */
    @ExceptionHandler
    public R exceptionHandler(Exception e, HttpServletResponse resp) {
        e.printStackTrace();
        resp.setStatus(500);
        String msg = e.getMessage();
        if (msg.contains("Duplicate entry")) {
            return R.err("你输入的用户名已重复", 500);
        } else {
            return R.err(e, 500);
        }


    }

    /**
     * 列出用户
     *
     * @param page     页
     * @param limit    限制
     * @param searchVO 搜索 VO
     * @return {@link R }
     */
    @PostMapping("/listUser/{page}/{limit}")
    @Transactional(rollbackFor = Exception.class)
    public R listUser(
            @PathVariable("page") Integer page,
            @PathVariable("limit") Integer limit,
            @RequestBody UserSearchVO searchVO) {
        Page voPage = new Page(page, limit);
        Page<UserVO> retPage = userService.listUser(voPage, searchVO);
        return R.ok(retPage.getRecords())
                .put("total", retPage.getTotal());
    }

    /**
     * 添加用户
     *
     * @param userVO 用户 VO
     * @return {@link R }
     */
    @PostMapping("/addUser")
    public R addUser(@RequestBody UserVO userVO) {
        userService.addUser(userVO);
        return R.ok();
    }

    /**
     * 更新用户
     *
     * @param userVO 用户 VO
     * @return {@link R }
     */
    @PutMapping("/updateUser")
    public R updateUser(@RequestBody UserVO userVO) {
        userService.updateUser(userVO);
        return R.ok();
    }

    /**
     * 获取用户
     *
     * @param id 身份证
     * @return {@link R }
     */
    @GetMapping("/getUser/{id}")
    public R getUser(@PathVariable("id") Integer id) {
        UserVO userVO = userService.getUser(id);
        return R.ok(userVO);
    }

    /**
     * 按令牌获取用户
     *
     * @param token 令 牌
     * @return {@link R }
     */
    @GetMapping("/getUser")
    public R getUserByToken(@RequestHeader("token")String token) {
        try {
            UserInfoVO userInfoVO = userService.getUserByToken(token);
            return R.ok(userInfoVO);
        }catch (Exception e) {
            e.printStackTrace();
            return R.err(e, 500);
        }
    }

    /**
     * 删除用户
     *
     * @param id 身份证
     * @return {@link R }
     */
    @DeleteMapping("/deleteUser/{id}")
    public R deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return R.ok().put("msg", "删除成功");
    }

    /**
     * 更新用户信息
     *
     * @param userInfoVO 用户信息 VO
     * @param token      令 牌
     * @return {@link R }
     */
    @PutMapping("/updateUserInfo")
    public R updateUserInfo(@RequestBody UserInfoVO userInfoVO,@RequestHeader("token")String token){
        userService.updateUserInfo(userInfoVO,token);
        return R.ok().put("msg","更新用户信息成功");
    }

    /**
     * 更新用户密码
     *
     * @param userVO 用户 VO
     * @param token  令 牌
     * @return {@link R }
     */
    @PutMapping("/updateUserPassword")
    public R updateUserPassword(@RequestBody UserVO userVO,@RequestHeader("token")String token){
        System.out.println(userVO.getPassword());
        userService.updateUserPassword(token,userVO.getPassword());
        return R.ok().put("msg","更新密码成功");
    }
}