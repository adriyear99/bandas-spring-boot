package com.springboot.adriano.entity;


import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "banda", schema = "musica")
@SequenceGenerator(name = "banda_id_seq", sequenceName = "musica.banda_id_seq", allocationSize = 1)
public class Banda implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "banda_id_seq")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "qtd_membros", nullable = false)
    private Integer qtdMembros;

    @ManyToOne
    @JoinColumn(name = "genero_id", nullable = false)
    private Genero genero;
}
