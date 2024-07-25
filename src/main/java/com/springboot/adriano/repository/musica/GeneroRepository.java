package com.springboot.adriano.repository.musica;

import com.springboot.adriano.entity.musica.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {


}
