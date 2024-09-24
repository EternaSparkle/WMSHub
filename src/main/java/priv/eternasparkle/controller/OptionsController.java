package priv.eternasparkle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.eternasparkle.entity.Options;
import priv.eternasparkle.mapper.OptionsMapper;
import priv.eternasparkle.util.R;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 选项控制器
 *
 * @author EternaSparkle
 * @version 1.0.0
 * @date 2024/09/24
 */
@RestController
@RequestMapping("/Options")
public class OptionsController {

    /**
     * 选项映射器
     */
    private final OptionsMapper optionsMapper;

    /**
     * 选项控制器
     *
     * @param optionsMapper 选项映射器
     */
    public OptionsController(OptionsMapper optionsMapper) {
        this.optionsMapper = optionsMapper;
    }

    /**
     * 异常处理程序
     *
     * @param e    e
     * @param resp 回复
     * @return {@link R }
     */
    @ExceptionHandler
    public R exceptionHandler(Exception e, HttpServletResponse resp) {
        e.printStackTrace();
        R err = R.err(e, 500);
        resp.setStatus(500);
        return err;
    }

    /**
     * 获取角色选项
     *
     * @return {@link R }
     */
    @GetMapping("/getRoleOptions")
    public R getRoleOptions() {
        List<Options> list = optionsMapper.getRoleOptions();
        return R.ok(list);
    }

    /**
     * 获取 dept 选项
     *
     * @return {@link R }
     */
    @GetMapping("/getDeptOptions")
    public R getDeptOptions() {
        List<Options> list = optionsMapper.getDeptOptions();
        return R.ok(list);
    }

}