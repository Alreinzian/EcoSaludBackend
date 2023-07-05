package com.ecoSalud.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecoSalud.springboot.app.models.entity.Historial;

public interface HistorialRepository extends CrudRepository<Historial, Integer> {

}
