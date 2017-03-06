package ru.third.inno.task.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yy on 06.03.17.
 */
@Controller
public class HelloController {

    @RequestMapping(name = "/hello")
    @ResponseBody
    public String  seyHello(){
        return "Hi";
    }
}
