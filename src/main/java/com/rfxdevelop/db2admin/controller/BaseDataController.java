package com.rfxdevelop.db2admin.controller;

import com.rfxdevelop.db2admin.model.BaseData;
import com.rfxdevelop.db2admin.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping(value = "/baseData")
public class BaseDataController {

    @Autowired
    private BaseData baseData;

    @RequestMapping("/get")
    public Result baseData(HttpServletRequest request) throws Exception {
        Result result = Result.successResult();
        result.put("baseData",baseData);
        return result;
    }
}
