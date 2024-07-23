package com.springboot.adriano.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "login", schema = "suporte")
@SequenceGenerator(name = "login_id_seq", sequenceName = "suporte.login_id_seq", allocationSize = 1)
public class Login {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_id_seq")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "data_hora_login", nullable = false)
    private LocalDateTime dataHoraLogin;
}
