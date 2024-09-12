package priv.eternasparkle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import priv.eternasparkle.entity.Options;

import java.util.List;

/**
 * @author EternaSparkle
 * @Date 2024/09/09
 * @Time 15:42
 */
public interface OptionsMapper extends BaseMapper<Options> {
    @Select("SELECT d.id AS value, d.dept_name AS label FROM tbl_dept d")
    List<Options> getDeptOptions();

    @Select("SELECT r.id AS value, r.role_name AS label FROM tbl_role r")
    List<Options> getRoleOptions();
}
