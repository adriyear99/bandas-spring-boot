package com.springboot.adriano.repository.musica;

import com.springboot.adriano.entity.musica.Banda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandaRepository extends JpaRepository<Banda,Long> {

    List<Banda> findAllByOrderByIdAsc();
}
