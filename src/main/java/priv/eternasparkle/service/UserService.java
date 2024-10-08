package priv.eternasparkle.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import priv.eternasparkle.entity.User;
import priv.eternasparkle.vo.CLeaderVO;
import priv.eternasparkle.vo.UserInfoVO;
import priv.eternasparkle.vo.UserSearchVO;
import priv.eternasparkle.vo.UserVO;

import java.util.List;

/**
 * @author EternaSparkle
 * @Date 2024/09/02
 * @Time 11:07
 */
public interface UserService extends IService<User> {
    User selectUserAndRoleByUserName(String username);

    Page<UserVO> listUser(Page page, UserSearchVO searchVO);

    void addUser(UserVO userVO);

    void updateUser(UserVO userVO);

    UserVO getUser(Integer id);

    void deleteUser(Integer id);

    UserInfoVO getUserByToken(String token);

    void updateUserInfo(UserInfoVO UserInfoVO,String token);

    void updateUserPassword(String token,String newPassword);

    List<CLeaderVO> getCLeaderByDeptId(String deptId,String word);


}
