package priv.eternasparkle.vo;

import lombok.Data;

import java.util.List;

@Data
public class DeptListVO {
    private String id;
    private String deptName;
    private String deptFullName;
    private String deptDesc;
    private Integer leaderId;
    private String leaderName;
    private List<DeptListVO> children;
    private Integer deptUserCount;
    private Boolean status;
}