package com.lecongtien.cinema.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SystemCinemaModel {
    private static final long serialVersionUID = 4785748493834L;

    @Length(max =50)
    @NotNull(message = "System cinema name not null")
    @NotBlank
    private String tenHtr;

    private String logo;

    private int hotCall;

    private String khuVuc;
}
