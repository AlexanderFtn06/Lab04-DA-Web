package com.tecsup.lab04.service;

import com.tecsup.lab04.models.Usuario;
import com.tecsup.lab04.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public Usuario registrar(Usuario usuario){
        return repo.save(usuario);
    }

    public List<Usuario> listar(){
        return repo.findAll();
    }

    public Usuario obtener(Long id){
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encotrado"));
    }

    public void eliminar(Long id){
        repo.deleteById(id);
    }
    public Usuario actualizar(Long id, Usuario nuevo) {
        Usuario existente = obtener(id);

        existente.setUsername(nuevo.getUsername());
        existente.setPassword(nuevo.getPassword());

        if (nuevo.getPerfil() != null) {
            existente.getPerfil().setNombreCompleto(nuevo.getPerfil().getNombreCompleto());
            existente.getPerfil().setDireccion(nuevo.getPerfil().getDireccion());
            existente.getPerfil().setTelefono(nuevo.getPerfil().getTelefono());
        }

        return repo.save(existente);
    }
}
