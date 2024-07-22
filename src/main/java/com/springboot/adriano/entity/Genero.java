package com.springboot.adriano.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "genero", schema = "musica")
@SequenceGenerator(name = "genero_id_seq", sequenceName = "musica.genero_id_seq", allocationSize = 1)
public class Genero implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genero_id_seq")
    private Long id;

    @Column(name = "nome")
    private String nome;

}
