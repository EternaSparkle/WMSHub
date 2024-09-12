package priv.eternasparkle.DTO;

import lombok.Data;
import priv.eternasparkle.entity.User;

import java.util.ArrayList;
import java.util.List;


@Data
public class UserDetailDTO {
    private User user;
    private List<String> permission = new ArrayList<>();
    public UserDetailDTO(){}
    public UserDetailDTO(User user,List<String> perms){
        this.user = user;
        this.permission =  perms;
    }
    public void addPermission(String perm){
        permission.add(perm);
    }
}
