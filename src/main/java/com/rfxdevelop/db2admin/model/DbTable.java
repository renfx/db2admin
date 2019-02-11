package com.rfxdevelop.db2admin.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data @Builder
public class DbTable {
    private String table_name;
    private String table_comment;
    private Map<String,DbColumn> columns;
}
