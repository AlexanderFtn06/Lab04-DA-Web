package com.tecsup.lab04.service;

import com.tecsup.lab04.models.Curso;
import com.tecsup.lab04.models.Estudiante;
import com.tecsup.lab04.repository.CursoRepository;
import com.tecsup.lab04.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepo;

    @Autowired
    private CursoRepository cursoRepo;

    public List<Estudiante> listar() {
        return estudianteRepo.findAll();
    }

    public Estudiante guardar(Estudiante estudiante) {

        Set<Curso> cursosPersistidos = new HashSet<>();

        if (estudiante.getCursos() != null) {
            for (Curso c : estudiante.getCursos()) {
                Curso curso = cursoRepo.findById(c.getId())
                        .orElseThrow(() -> new RuntimeException("Curso no existe: id " + c.getId()));
                cursosPersistidos.add(curso);
            }
        }

        estudiante.setCursos(cursosPersistidos);
        return estudianteRepo.save(estudiante);
    }

    public Estudiante obtener(Long id) {
        return estudianteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    public void eliminar(Long id) {
        estudianteRepo.deleteById(id);
    }

    public Estudiante agregarCurso(Long estudianteId, Long cursoId) {

        Estudiante estudiante = obtener(estudianteId);

        Curso curso = cursoRepo.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        boolean yaInscrito = estudiante.getCursos().stream()
                .anyMatch(c -> c.getId().equals(cursoId));

        if (yaInscrito) {
            throw new RuntimeException("El estudiante ya está inscrito en este curso");
        }

        estudiante.getCursos().add(curso);
        return estudianteRepo.save(estudiante);
    }

    public Estudiante quitarCurso(Long estudianteId, Long cursoId) {

        Estudiante estudiante = obtener(estudianteId);

        boolean existia = estudiante.getCursos().removeIf(c -> c.getId().equals(cursoId));

        if (!existia) {
            throw new RuntimeException("El estudiante no estaba inscrito en ese curso");
        }

        return estudianteRepo.save(estudiante);
    }

    // Listar los cursos de un estudiante en particular
    public Set<Curso> listarCursos(Long estudianteId) {
        Estudiante estudiante = obtener(estudianteId);
        return estudiante.getCursos();
    }
}