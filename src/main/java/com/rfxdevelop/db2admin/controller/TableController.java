package com.rfxdevelop.db2admin.controller;

import com.rfxdevelop.db2admin.dao.dynamic.IMapper;
import com.rfxdevelop.db2admin.model.BaseData;
import com.rfxdevelop.db2admin.model.DbTable;
import com.rfxdevelop.db2admin.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@RestController
@RequestMapping(value = "/simple/table")
public class TableController {

    @Autowired(required = false)
    private IMapper iMapper;
    @Autowired
    private BaseData baseData;

    @RequestMapping("/get")
    public Result baseData(HttpServletRequest request, @RequestParam String tableName) throws Exception {
        DbTable dbTable = baseData.getTableMap().get(tableName);
        Result result = Result.successResult();
        result.put("dbTable",dbTable);
        return result;
    }

    @RequestMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response, @RequestParam String tableName) throws Exception {

    }
}
