package priv.eternasparkle.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author EternaSparkle
 * @Date 2024/09/02
 * @Time 10:38
 */

@Data
public class Permission implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;
    private int id;
    private String permName;
    private String pid;
    private String menuId;
    private Role role;
}
