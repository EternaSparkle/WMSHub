package priv.eternasparkle.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserSearchVO {
    //用来模糊搜索
    private String username;
    private String nickName;
    private String phone;
    //用来精确搜索
    private Integer deptId;
    private Integer roleId;

    //年龄上下限
    private Integer ageStart;
    private Integer ageEnd;

    //多用户搜索
    private List<String> names;
}