package com.rfxdevelop.db2admin.init;

import com.rfxdevelop.db2admin.dao.information.SysMapper;
import com.rfxdevelop.db2admin.model.BaseData;
import com.rfxdevelop.db2admin.model.DbColumn;
import com.rfxdevelop.db2admin.model.DbTable;
import lombok.extern.java.Log;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Component
@Log
public class InitTables implements CommandLineRunner {

    @Autowired(required = false)
    private SysMapper sysMapper;
    @Autowired
    private BaseData baseData;
    @Value("${tableSchema}")
    private String tableSchema;

    @Override
    public void run(String... args) throws Exception {
        Map<String,DbTable> tableMap = new HashMap<>();
        List<String> tableSchemas = new ArrayList<>();
        tableSchemas.add(tableSchema);
        List<Map<String, Object>> tableList = sysMapper.selectTables(tableSchemas);
        tableList.stream().forEach(map->{
            String table_name = MapUtils.getString(map, "table_name");
            String column_name = MapUtils.getString(map, "column_name");
            String table_comment = MapUtils.getString(map, "table_comment");
            boolean _key = MapUtils.getBooleanValue(map, "_key");
            String primaryKey = null;
            if(_key){
                primaryKey = column_name;
            }
            DbColumn dbColumn = DbColumn.builder().build();
            try {
                BeanUtils.populate(dbColumn,map);
            } catch (IllegalAccessException e) {
                log.info("初始化Table报错："+e.getMessage());
            } catch (InvocationTargetException e) {
                log.info("初始化Table报错："+e.getMessage());
            }
            Map<String, DbColumn> columns;
            DbTable dbTable;
            if(tableMap.containsKey(table_name)){
                dbTable = tableMap.get(table_name);
                columns = dbTable.getColumnMap();

            }else{
                columns = new LinkedHashMap<>();
                dbTable = DbTable.builder()
                        .table_name(table_name)
                        .table_comment(table_comment)
                        .columnMap(columns).build();

                tableMap.put(table_name,dbTable);
            }
            if(StringUtils.hasText(primaryKey) && !StringUtils.hasText(dbTable.getPrimaryKey())){
                dbTable.setPrimaryKey(primaryKey);
            }
            columns.put(column_name,dbColumn);
        });
        //初始化表名数据
        baseData.setTableMap(tableMap);
    }
}
