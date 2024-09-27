package priv.eternasparkle.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import priv.eternasparkle.entity.User;
import priv.eternasparkle.mapper.RoleMapper;
import priv.eternasparkle.mapper.UserMapper;
import priv.eternasparkle.service.UserService;
import priv.eternasparkle.templates.TokenTemplate;
import priv.eternasparkle.vo.CLeaderVO;
import priv.eternasparkle.vo.UserInfoVO;
import priv.eternasparkle.vo.UserSearchVO;
import priv.eternasparkle.vo.UserVO;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author EternaSparkle
 * @Date 2024/09/02
 * @Time 11:08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final TokenTemplate tokenTemplate;

    public UserServiceImpl(UserMapper userMapper, RoleMapper roleMapper, TokenTemplate tokenTemplate) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.tokenTemplate = tokenTemplate;
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

    @Override
    public void deleteUser(Integer id) {
       userMapper.clearUserRole(id);
        int cnt = userMapper.deleteById(id);
        if (cnt != 1) {
            throw new RuntimeException("删除用户失败。");
        }
    }

    @Override
    public UserInfoVO getUserByToken(String token) {
        int userId = (int) tokenTemplate.getValue(token,"userId");
        return userVoToUserInfoVO(userMapper.getUserById(userId));
    }

    @Override
    public void updateUserInfo(UserInfoVO UserInfoVO,String token) {
        int userId = (int) tokenTemplate.getValue(token,"userId");
        UserVO userVO = new UserVO();
        userVO.setId(userId);
        userVO.setNickName(UserInfoVO.getNickName());
        userVO.setSex(UserInfoVO.getSex());
        userVO.setAge(UserInfoVO.getAge());
        userVO.setPhone(UserInfoVO.getPhone());
        userVO.setEmail(UserInfoVO.getEmail());
        updateUser(userVO);
    }

    @Override
    public void updateUserPassword(String token, String newPassword) {
        int userId = (int) tokenTemplate.getValue(token,"userId");
        UserVO userVO = new UserVO();
        userVO.setId(userId);
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        userVO.setPassword(encoder.encode(newPassword));
        updateUser(userVO);
    }

    @Override
    public List<CLeaderVO> getCLeaderByDeptId(String deptId, String word) {
        System.out.println("deptId = " + deptId + ", word = " + word);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_id", deptId).like("nick_name", word);
        List<User> users = userMapper.selectList(queryWrapper);
        return users.stream().map(this::userToCLeaderVO).collect(Collectors.toList());
    }

    private CLeaderVO userToCLeaderVO(User user) {
        CLeaderVO cLeaderVO = new CLeaderVO();
        cLeaderVO.setId(user.getId());
        cLeaderVO.setNickname(user.getNickName());
        return cLeaderVO;
    }


    private User voToPO(UserVO userVO) {
        User user = new User();
        user.setId(userVO.getId());
        user.setNo(userVO.getNo());
        user.setUsername(userVO.getUsername());
        user.setPassword(userVO.getPassword());
        user.setNickName(userVO.getNickName());
        user.setSex(userVO.getSex());
        user.setAge(userVO.getAge());
        user.setDeptId(userVO.getDeptId());
        user.setPhone(userVO.getPhone());
        user.setEmail(userVO.getEmail());
        return user;
    }

    private UserInfoVO userVoToUserInfoVO(UserVO userVO) {
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUsername(userVO.getUsername());
        userInfoVO.setNickName(userVO.getNickName());
        userInfoVO.setPhone(userVO.getPhone());
        userInfoVO.setSex(userVO.getSex());
        userInfoVO.setAge(userVO.getAge());
        userInfoVO.setEmail(userVO.getEmail());
        return userInfoVO;
    }
}
