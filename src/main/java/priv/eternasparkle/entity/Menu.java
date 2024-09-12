package priv.eternasparkle.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Menu {
    private String id;
    private String parentId;
    private Integer permissionId;
    private String permission;
    private String menuName;
    private String path;
    private String mapping;
    private String icon;
    private String component;
    private String isMenu = "0";
    private String isAdmin = "0";
    private List<Menu> children = new ArrayList<>();

    public Menu(){ }
    public Menu(String line){
        String[] part = line.split(",");
        this.id = part[0];
        this.menuName = part[1];
        this.parentId  = part[2];
        this.mapping = part[3];
        this.path = part[4];
        this.permission = part[5];
        this.component = part[6];
        this.icon = part[7];
        this.isMenu = part[8];
    }

}
