package com.ecoSalud.springboot.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecoSalud.springboot.app.models.entity.Usuario;
import com.ecoSalud.springboot.app.repository.UsuarioRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crear(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarTodo() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).get();
    }

    public Usuario actualizar(Usuario usuarioActualizar) {
        Usuario usuarioActual = usuarioRepository.findById(usuarioActualizar.getId_usuario()).get();
        
        usuarioActual.setId_usuario(usuarioActualizar.getId_usuario());
        usuarioActual.setNombres(usuarioActualizar.getNombres());
        usuarioActual.setApellidoMaterno(usuarioActualizar.getApellidoMaterno());
        usuarioActual.setApellidoPaterno(usuarioActualizar.getApellidoPaterno());
        usuarioActual.setFechaNac(usuarioActualizar.getFechaNac());
        usuarioActual.setCorreo(usuarioActualizar.getCorreo());
        usuarioActual.setPassword(usuarioActualizar.getPassword());
        usuarioActual.setDni(usuarioActualizar.getDni());
        usuarioActual.setTelefono(usuarioActualizar.getTelefono());
        usuarioActual.setDireccion(usuarioActualizar.getDireccion());

        Usuario usuarioActualizado = usuarioRepository.save(usuarioActual);
        return usuarioActualizado;
    }

    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}

