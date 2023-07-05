package com.ecoSalud.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecoSalud.springboot.app.models.entity.Horario;

public interface HorarioRepository extends CrudRepository<Horario, Integer> {

}
