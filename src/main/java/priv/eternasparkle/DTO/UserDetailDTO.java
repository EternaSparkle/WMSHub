package priv.eternasparkle.DTO;

import lombok.Data;
import priv.eternasparkle.entity.User;

import java.util.ArrayList;
import java.util.List;


/**
 * 用户详细信息 DTO
 *
 * @author EternaSparkle
 * @version 1.0.0
 * @date 2024/09/24
 */
@Data
public class UserDetailDTO {
    private User user;
    private List<String> permission = new ArrayList<>();

    /**
     * 用户详细信息 DTO
     */
    public UserDetailDTO(){}

    /**
     * 用户详细信息 DTO
     *
     * @param user  用户
     * @param perms 烫发
     */
    public UserDetailDTO(User user,List<String> perms){
        this.user = user;
        this.permission =  perms;
    }

    /**
     * 添加权限
     *
     * @param perm 权限列表
     */
    public void addPermission(String perm){
        permission.add(perm);

    }
}
