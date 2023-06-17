package com.ecoSalud.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecoSalud.springboot.app.models.entity.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor,Integer> {

}
