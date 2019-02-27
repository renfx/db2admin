package com.rfxdevelop.db2admin.controller;

import com.rfxdevelop.db2admin.dao.dynamic.IMapper;
import com.rfxdevelop.db2admin.model.BaseData;
import com.rfxdevelop.db2admin.model.DbTable;
import com.rfxdevelop.db2admin.utils.WhereList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private IMapper iMapper;
    @Autowired
    private BaseData baseData;

    @RequestMapping("/view/{tableName}.html")
    public ModelAndView right(@PathVariable("tableName") String tableName,ModelAndView modelAndView) throws Exception {
        modelAndView.setViewName("right");
        DbTable dbTable = baseData.getTableMap().get(tableName);
        modelAndView.addObject("tableName",tableName);
        modelAndView.addObject("tableData", dbTable);
        return modelAndView;
    }
    @RequestMapping("/view/{tableName}/{viewName}.html")
    public ModelAndView view(@PathVariable("tableName") String tableName, @PathVariable("viewName") String viewName, ModelAndView modelAndView, HttpServletRequest request) throws Exception {
        modelAndView.setViewName(viewName);
        DbTable dbTable = baseData.getTableMap().get(tableName);
        modelAndView.addObject("tableName",tableName);
        modelAndView.addObject("tableData", dbTable);
        String primaryKey = dbTable.getPrimaryKey();
        String keyValue = request.getParameter(primaryKey);
        if(StringUtils.hasText(keyValue)){
            List<Map<String, Object>> whereListMap = WhereList.builder().addCondition(primaryKey, "=", keyValue).getWhereListMap();
            modelAndView.addObject("bean",iMapper.selectOne(whereListMap,tableName,null));
        }
        return modelAndView;
    }

    @RequestMapping("/{viewName}.html")
    public ModelAndView main(@PathVariable("viewName") String viewName,ModelAndView modelAndView) throws Exception {
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
