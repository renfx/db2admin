package com.rfxdevelop.db2admin.dao.dynamic;

import lombok.NonNull;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IMapper {

    /**
     * 查找
     * @param whereList 过滤条件 {"key":"index","separator":"=","value":"0"}
     * @param tableName 表名
     * @param columns 列
     * @return List<Map>
     */
    List<Map<String,Object>> select(@Param("whereList") List<Map> whereList, @NonNull @Param("tableName") String tableName, @Param("columns") List<String> columns, @Param("pageQuery") Map<String, Object> pageQuery);

    /**
     *  查找数量
     * @param whereList 过滤条件 {"key":"index","separator":"=","value":"0"}
     * @param tableName 表名
     * @param columns 列
     * @return Long
     */
    Long selectCount(@Param("whereList") List<Map> whereList, @NonNull @Param("tableName") String tableName, @Param("columns") List<String> columns);

    /**
     *  更新
     * @param whereList 过滤条件
     * @param tableName 表名
     * @param modifyMap 修改的列和值
     * @return
     */
    void update(@Param("whereList") List<Map> whereList, @NonNull @Param("tableName") String tableName, @NonNull @Param("modifyMap") Map<String, Object> modifyMap);

    /**
     * 新增
     * @param tableName
     * @param insertMap
     */
    void insert(@NonNull @Param("tableName") String tableName, @NonNull @Param("insertMap") Map<String, Object> insertMap);

    /**
     * 删除
     * @param whereList
     * @param tableName
     */
    void delete(@Param("whereList") List<Map> whereList, @NonNull @Param("tableName") String tableName);

    /**
     * 查找一条数据
     * @param whereList 过滤条件 {"key":"index","separator":"=","value":"0"}
     * @param tableName 表名
     * @param columns 列
     * @return
     */
    Map<String,Object> selectOne(@Param("whereList") List<Map<String, Object>> whereList, @NonNull @Param("tableName") String tableName, @Param("columns") List<String> columns);

}
