package com.lecongtien.cinema.model;

import lombok.*;

import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcelErrorDetail {
    Integer rowIndex;
    HashMap<String,String> details;
}
