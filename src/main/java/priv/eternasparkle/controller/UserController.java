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
 * @author EternaSparkle
 * @Date 2024/09/09
 * @Time 11:25
 */
@RestController
@RequestMapping("/System/User")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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

    @PostMapping("/addUser")
    public R addUser(@RequestBody UserVO userVO) {
        userService.addUser(userVO);
        return R.ok();
    }
    @PutMapping("/updateUser")
    public R updateUser(@RequestBody UserVO userVO) {
        userService.updateUser(userVO);
        return R.ok();
    }

    @GetMapping("/getUser/{id}")
    public R getUser(@PathVariable("id") Integer id) {
        UserVO userVO = userService.getUser(id);
        return R.ok(userVO);
    }

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

    @DeleteMapping("/deleteUser/{id}")
    public R deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return R.ok().put("msg", "删除成功");
    }

    @PutMapping("/updateUserInfo")
    public R updateUserInfo(@RequestBody UserInfoVO userInfoVO,@RequestHeader("token")String token){
        userService.updateUserInfo(userInfoVO,token);
        return R.ok().put("msg","更新用户信息成功");
    }

    @PutMapping("/updateUserPassword")
    public R updateUserPassword(@RequestBody UserVO userVO,@RequestHeader("token")String token){
        System.out.println(userVO.getPassword());
        userService.updateUserPassword(token,userVO.getPassword());
        return R.ok().put("msg","更新密码成功");
    }
}