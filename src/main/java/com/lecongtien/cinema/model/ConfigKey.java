package com.lecongtien.cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ConfigKey {
    String key;

    String type;

    @JsonProperty("isRequired")
    boolean isRequired;

    String validation;

    List<Integer> backGroundColor;

    List<Integer> fontColor;

    @JsonProperty("style")
    boolean style;

}
