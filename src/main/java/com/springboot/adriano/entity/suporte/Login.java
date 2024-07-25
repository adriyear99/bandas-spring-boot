package com.springboot.adriano.entity.suporte;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "login", schema = "suporte")
public class Login {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "data_hora_login", nullable = false)
    private LocalDateTime dataHoraLogin;
}
