package com.springboot.adriano.dto.musica;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChartRequest {
    private String name;
    private String textColor;
    private String backgroundColor;
    private Integer items;
    private Integer selectionType;
    private List<Resultado> resultados;
}
