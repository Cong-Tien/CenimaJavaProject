package com.lecongtien.cinema.model;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class ExcelConfigModel {
    HashMap<String,String> mappingRule;
    Style style;
    List<String> headers;
    String fileName;
    List<ConfigKey> configKey;
    Integer startRow;
    Integer startColumn;
}
