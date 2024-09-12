package priv.eternasparkle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.eternasparkle.util.R;

@RestController
@RequestMapping("/Axios")
public class AxiosController {
    //Link: /Axios/data1
    @GetMapping("/data1")
    public R data1(){
        System.out.println("+---------------------------+");
        System.out.println("{Axios}成功访问这个方法..");
        System.out.println("+---------------------------+");
        return R.ok().put("content","sayHello");
    }
}

