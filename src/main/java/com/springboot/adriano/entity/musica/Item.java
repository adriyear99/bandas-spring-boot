package com.springboot.adriano.entity.musica;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items", schema = "musica")
public class Item {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chart_id", nullable = false)
    private Chart chartId;

    @Column(name = "artist", nullable = false)
    private String artist;

    @Column(name = "album", nullable = false)
    private String album;

    @Column(name = "track", nullable = false)
    private String track;

    @Column(name = "position", nullable = false)
    private Integer position;

    @Column(name = "img", nullable = false)
    private String img;

}
