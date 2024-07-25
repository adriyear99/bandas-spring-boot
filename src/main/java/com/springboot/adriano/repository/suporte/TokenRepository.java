package com.springboot.adriano.repository.suporte;

import java.util.List;
import java.util.Optional;
import com.springboot.adriano.entity.suporte.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t from Token t inner join Usuario u\s
      on t.usuario.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUsuario(Long id);

    Optional<Token> findByToken(String token);
}
