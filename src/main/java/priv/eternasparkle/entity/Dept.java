package priv.eternasparkle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author EternaSparkle
 * @Date 2024/09/13
 * @Time 9:31
 */
@Data
public class Dept {
    @TableId(type = IdType.AUTO)
    private String id;
    private String deptName;
    private String deptDesc;
    private String deptFullName;
    private String parentId;
    private String pIds;
    private Integer leaderId;
    private Boolean status;
    private String deptPhone;
    private String deptAddress;
    private String deptCode;
    private String deptType;
}