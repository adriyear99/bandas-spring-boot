package com.springboot.adriano.repository.musica;

import com.springboot.adriano.entity.musica.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Long> {

}
