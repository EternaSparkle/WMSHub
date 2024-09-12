package priv.eternasparkle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import priv.eternasparkle.entity.Menu;

import java.util.List;

public interface MenuMapper
        extends BaseMapper<Menu> {

    List<Menu> getMenuByRole(@Param("roleId")Integer roleId);

}


