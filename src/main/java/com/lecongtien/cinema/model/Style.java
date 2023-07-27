package com.lecongtien.cinema.model;

import lombok.Data;

@Data
public class Style {
    String sheetName;
    StyleExcelModel body;
    StyleExcelModel header;
}
