package priv.eternasparkle.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import priv.eternasparkle.entity.User;
import priv.eternasparkle.vo.UserSearchVO;
import priv.eternasparkle.vo.UserVO;

/**
 * @author EternaSparkle
 * @Date 2024/09/02
 * @Time 11:01
 */

public interface UserMapper extends BaseMapper<User> {
    User selectUserAndRoleByUserName(String username);

    Page<UserVO> queryList(@Param("page") Page page, @Param("search") UserSearchVO searchVO);

    @Insert("insert into tbl_user_role(user_id, role_id) values(#{userId}, #{roleId})")
    int setUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    @Delete("delete from tbl_user_role where user_id=#{userId}")
    int clearUserRole(@Param("userId") Integer userId);

    UserVO getUserById(@Param("id") Integer id);
}
