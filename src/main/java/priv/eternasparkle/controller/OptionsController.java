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

@RestController
@RequestMapping("/Options")
public class OptionsController {

    private final OptionsMapper optionsMapper;

    public OptionsController(OptionsMapper optionsMapper) {
        this.optionsMapper = optionsMapper;
    }

    @ExceptionHandler
    public R exceptionHandler(Exception e, HttpServletResponse resp) {
        e.printStackTrace();
        R err = R.err(e, 500);
        resp.setStatus(500);
        return err;
    }

    @GetMapping("/getRoleOptions")
    public R getRoleOptions() {
        List<Options> list = optionsMapper.getRoleOptions();
        return R.ok(list);
    }

    @GetMapping("/getDeptOptions")
    public R getDeptOptions() {
        List<Options> list = optionsMapper.getDeptOptions();
        return R.ok(list);
    }

}