package com.springboot.adriano.dto.musica;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Resultado {
    private Integer id;
    private String artist;
    private String album;
    private String track;
    private String img;
}
