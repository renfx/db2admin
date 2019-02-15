package com.rfxdevelop.db2admin.dao.information;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysMapper {
    List<Map<String,Object>> selectTables(@Param("tableSchemas") List<String> tableSchemas);
}
