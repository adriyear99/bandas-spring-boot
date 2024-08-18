package com.springboot.adriano.repository.musica;

import com.springboot.adriano.entity.musica.Chart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartRepository extends JpaRepository<Chart, Long> {

}
