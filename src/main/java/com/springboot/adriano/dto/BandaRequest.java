package com.springboot.adriano.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BandaRequest {
    private String nome;
    private Long idGenero;
    private Integer qtdMembros;
}
