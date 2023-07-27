package com.lecongtien.cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class StyleExcelModel {

    String font;

    List<Integer> backGroundColor;

    @JsonProperty("isBold")
    boolean isBold;

    @JsonProperty("isItalic")
    boolean isItalic;

    Integer fontWeight;

    Integer fontColor;

    @JsonProperty("border")
    boolean border;

    Integer borderStyle;
}
