package priv.eternasparkle.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.eternasparkle.util.R;

@RestController
@RequestMapping("/Dept")
public class DeptController {
    //Link: /Dept/view
    @RequestMapping("/view")
    public R view(){
        return R.ok().put("viewName","/View");
    }
    //Link: /Dept/edit
    @RequestMapping("/edit")
    public R edit(){
        return R.ok().put("viewName","/Edit");
    }
    //Link: /Dept/delete
    @RequestMapping("/delete")
    public R delete(){
        return R.ok().put("viewName","/Delete");
    }
}
