package com.iot.quickhpu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public String listUser() {
        return "user列表";
    }

    @RequestMapping("index")
    public String index() {
        System.out.println("index");
        return "index";
    }

}
