package priv.eternasparkle.entity;

import lombok.Data;

/**
 * @author EternaSparkle
 * @Date 2024/09/13
 * @Time 9:31
 */
@Data
public class Dept {
    private String id;
    private String deptName;
    private String deptDesc;
    private String parentId;
    private String pIds;
    private Integer leaderId;
}