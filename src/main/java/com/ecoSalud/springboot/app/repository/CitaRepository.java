package com.ecoSalud.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecoSalud.springboot.app.models.entity.Cita;

public interface CitaRepository extends CrudRepository<Cita, Integer> {

}
