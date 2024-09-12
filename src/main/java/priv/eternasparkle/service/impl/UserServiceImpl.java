package priv.eternasparkle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.eternasparkle.entity.User;
import priv.eternasparkle.mapper.UserMapper;
import priv.eternasparkle.service.UserService;
import priv.eternasparkle.vo.UserSearchVO;
import priv.eternasparkle.vo.UserVO;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author EternaSparkle
 * @Date 2024/09/02
 * @Time 11:08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User selectUserAndRoleByUserName(String username) {
        return userMapper.selectUserAndRoleByUserName(username);
    }

    @Override
    public Page<UserVO> listUser(Page page, UserSearchVO searchV0){
        Page rePage = userMapper.queryList(page, searchV0);
        return rePage;
    }

    @Override
    public void addUser(UserVO userVO) {
        User user = voToPO(userVO);
        int cnt = userMapper.insert(user);
        Integer userId = user.getId();
        Integer roleId = userVO.getRoleId();


        userMapper.clearUserRole(userId);
        cnt += userMapper.setUserRole(userId, roleId);

        if (cnt != 2) {
            throw new RuntimeException("添加用户失败。");
        }
    }

    @Override
    public void updateUser(UserVO userVO) {
        // {1} 做VO与PO的转换
        User user = voToPO(userVO);

        user.setId(userVO.getId());

        int cnt = userMapper.updateById(user);
        Integer roleId = userVO.getRoleId();
        Integer userId = user.getId();

        // {2} 清除它原来的角色
        userMapper.clearUserRole(userId);
        cnt += userMapper.setUserRole(userId, roleId);

        if (cnt != 2) {
            throw new RuntimeException("修改用户失败。");
        }
    }

    @Override
    public UserVO getUser(Integer id) {
        UserVO user = userMapper.getUserById(id);
        if (user == null) {
            throw new RuntimeException("获取用户失败。");
        }
        return user;
    }


    private User voToPO(UserVO userVO) {
        User user = new User();
        user.setNo(userVO.getNo());
        user.setUsername(userVO.getUsername());
        user.setNickName(userVO.getNickName());
        user.setSex(userVO.getSex());
        user.setDeptId(userVO.getDeptId());
        user.setPhone(userVO.getPhone());
        user.setEmail(userVO.getEmail());
        return user;
    }
}
