package com.tecsup.lab04.repository;

import com.tecsup.lab04.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
