package com.rfxdevelop.db2admin.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
public class BaseData {
    private Map<String,DbTable> tableMap;
}
