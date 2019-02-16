package com.rfxdevelop.db2admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @RequestMapping("/table/{tableName}.html")
    public ModelAndView test(@PathVariable("tableName") String tableName,ModelAndView modelAndView) throws Exception {
        modelAndView.setViewName("right");
        modelAndView.addObject("tableName",tableName);
        return modelAndView;
    }

    @RequestMapping("/{viewName}.html")
    public ModelAndView main(@PathVariable("viewName") String viewName,ModelAndView modelAndView) throws Exception {
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
