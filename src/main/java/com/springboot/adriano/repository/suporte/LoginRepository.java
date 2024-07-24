package com.springboot.adriano.repository.suporte;

import com.springboot.adriano.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Login> {
}
