package priv.eternasparkle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import priv.eternasparkle.entity.Dept;
import priv.eternasparkle.mapper.DeptMapper;
import priv.eternasparkle.service.DeptService;
import priv.eternasparkle.vo.CLeaderVO;

import java.util.List;

/**
 * @author EternaSparkle
 * @Date 2024/09/20
 * @Time 11:50
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

}
