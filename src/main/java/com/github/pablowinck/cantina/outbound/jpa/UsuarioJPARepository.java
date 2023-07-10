package com.github.pablowinck.cantina.outbound.jpa;

import com.github.pablowinck.cantina.core.entity.Usuario;
import com.github.pablowinck.cantina.core.entity.repository.UsuarioRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJPARepository extends UsuarioRepository, JpaRepository<Usuario, String> {

}
