package priv.eternasparkle.vo;

import lombok.Data;

@Data
public class UserVO {
    private Integer id;
    private String username;
    private String password;
    private String nickName;
    private String email;
    private String phone;
    private String sex;
    private String age;
    private String no;
    private Integer deptId;

    private Integer roleId;
    private String roleName;
    private String deptName;
    private String status;
}
