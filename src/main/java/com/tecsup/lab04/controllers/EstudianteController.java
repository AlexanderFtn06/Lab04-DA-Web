package com.tecsup.lab04.controllers;

import com.tecsup.lab04.models.Curso;
import com.tecsup.lab04.models.Estudiante;
import com.tecsup.lab04.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService service;

    @GetMapping
    public List<Estudiante> listar() {
        return service.listar();
    }

    @PostMapping
    public Estudiante guardar(@RequestBody Estudiante estudiante) {
        return service.guardar(estudiante);
    }

    @GetMapping("/{id}")
    public Estudiante obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @PostMapping("/{estudianteId}/cursos/{cursoId}")
    public Estudiante agregarCurso(@PathVariable Long estudianteId, @PathVariable Long cursoId) {
        return service.agregarCurso(estudianteId, cursoId);
    }

    @DeleteMapping("/{estudianteId}/cursos/{cursoId}")
    public Estudiante quitarCurso(@PathVariable Long estudianteId, @PathVariable Long cursoId) {
        return service.quitarCurso(estudianteId, cursoId);
    }

    @GetMapping("/{estudianteId}/cursos")
    public Set<Curso> listarCursos(@PathVariable Long estudianteId) {
        return service.listarCursos(estudianteId);
    }
}