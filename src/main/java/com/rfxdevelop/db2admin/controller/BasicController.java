package com.rfxdevelop.db2admin.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.rfxdevelop.db2admin.dao.dynamic.IMapper;
import com.rfxdevelop.db2admin.dao.dynamic.exception.MapperParamException;
import com.rfxdevelop.db2admin.model.Result;
import com.rfxdevelop.db2admin.utils.RequestParamUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Stream;


@Slf4j
@RestController
@RequestMapping(value = "/basic")
public class BasicController {

    @Resource
    private IMapper iMapper;


    @RequestMapping("/{method}")
    public Result entrance(HttpServletRequest request, @PathVariable("method") String methodStr, @RequestParam String tableName) throws Exception {
        Result result = Result.successResult();
        Map<String, String> params = RequestParamUtil.getParams(request);
        toPageQuery(params);
        String[] methods = methodStr.split("-");
        for(String method:methods){
            Method mapperMethod = Stream.of(IMapper.class.getDeclaredMethods())
                    .filter(m -> m.getName().equals(method))
                    .findAny()
                    .orElseThrow(() -> new MapperParamException("method not found"));
            Object[] methodParams = getMethodParams(mapperMethod,params);
            Object invoke = mapperMethod.invoke(iMapper, methodParams);
            result.put(method,invoke);
        }
        return result;
    }

    private void toPageQuery(Map<String, String> params){
        String pageQuery = params.get("pageQuery");
        String page = params.get("page");
        String limit = params.get("limit");
        String orderBy = params.get("orderBy");
        String orderType = params.get("orderType");
        JSONObject  pageQueryMap = new JSONObject();
        if(StringUtils.hasText(page) && StringUtils.hasText(limit)){
            pageQueryMap.put("pageNo",Integer.parseInt(page));
            pageQueryMap.put("pageSize",Integer.parseInt(limit));
        }
        if(StringUtils.hasText(orderBy) &&StringUtils.hasText(orderType)){
            pageQueryMap.put("orderBy",orderBy);
            pageQueryMap.put("orderType",orderType);
        }
        if(!StringUtils.hasText(pageQuery)){
            params.put("pageQuery",pageQueryMap.toJSONString());
        }
    }


    /**
     * 将请求参数转为Mapper参数
     * @param mapperMethod Mapper执行的方法
     * @param params       Http请求参数
     * @return
     */
    private Object[]  getMethodParams(Method mapperMethod,Map<String, String> params){
        Object[] methodParams = new Object[mapperMethod.getParameterCount()];
        final int[] i = {0};
        Stream.of(mapperMethod.getParameterAnnotations()).forEach(s->{
            Stream.of(s).filter(a->a.annotationType().equals(Param.class))
                    .map(a->(Param)a)
                    .forEach(p->{
                        String paramName = p.value();
                        String paramValue = params.get(paramName);
                        try{
                            methodParams[i[0]] = JSONUtils.parse(paramValue);
                        }catch (Exception e){
                            methodParams[i[0]] = paramValue;
                        }
                        i[0]++;
                    });
        });
        return methodParams;
    }
}
