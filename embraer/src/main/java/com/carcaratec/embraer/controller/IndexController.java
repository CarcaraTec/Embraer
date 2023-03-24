package com.carcaratec.embraer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @RequestMapping(value = "/home")
    public ModelAndView home() {
        ModelAndView andView = new ModelAndView("home.html");
        return andView;
    }
}
