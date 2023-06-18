package com.ecoSalud.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecoSalud.springboot.app.models.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

}
