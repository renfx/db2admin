package com.rfxdevelop.db2admin.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data @Builder
public class DbTable implements Serializable {
    private String table_name;
    private String table_comment;
    private String primaryKey;
    private Map<String,DbColumn> columnMap;
}
