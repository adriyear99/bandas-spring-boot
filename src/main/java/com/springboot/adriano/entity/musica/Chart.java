package com.springboot.adriano.entity.musica;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chart", schema = "musica")
public class Chart implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "text_color", nullable = false)
    private String textColor;

    @Column(name = "background_color", nullable = false)
    private String backgroundColor;

    @Column(name = "items", nullable = false)
    private Integer items;

    @Column(name = "selection_type", nullable = false)
    private Integer selectionType;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

}
