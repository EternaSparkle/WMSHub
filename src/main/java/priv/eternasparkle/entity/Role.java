package priv.eternasparkle.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author EternaSparkle
 * @Date 2024/09/02
 * @Time 10:38
 */


@Data
public class Role implements Serializable{
    @Serial
    private static final long serialVersionUID = -2214766052209434981L;
    private Integer id;
    private String roleName;
    private String roleDesc;
    private String createDate;
    private String updateDate;
    List<Permission> permissions;
    private String isSystem = "0";
}
