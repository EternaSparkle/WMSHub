package priv.eternasparkle.vo;

import lombok.Data;

/**
 * @author EternaSparkle
 * @Date 2024/09/27
 * @Time 10:46
 */
@Data
public class DeptVO {
    private String lastDeptName;
    private String deptName;
    private String deptFullName;


    private String id;

    private String deptDesc;
    private String parentId;
    private String pIds;
    private Integer leaderId;
    private Boolean status;
    private String deptPhone;
    private String deptAddress;
    private String deptCode;
    private String deptType;
}
