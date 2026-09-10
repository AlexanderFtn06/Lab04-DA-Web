package com.tecsup.lab04.repository;

import com.tecsup.lab04.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}