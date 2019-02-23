package com.rfxdevelop.db2admin.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.rfxdevelop.db2admin.model.BaseData;
import com.rfxdevelop.db2admin.model.DbTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private BaseData baseData;

    @RequestMapping("/view/{tableName}.html")
    public ModelAndView view(@PathVariable("tableName") String tableName,ModelAndView modelAndView) throws Exception {
        modelAndView.setViewName("right");
        DbTable dbTable = baseData.getTableMap().get(tableName);
        String jsonString = JSONObject.toJSONString(dbTable);
        modelAndView.addObject("tableName",tableName);
        modelAndView.addObject("tableData", dbTable);
        return modelAndView;
    }

    @RequestMapping("/{viewName}.html")
    public ModelAndView main(@PathVariable("viewName") String viewName,ModelAndView modelAndView) throws Exception {
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
