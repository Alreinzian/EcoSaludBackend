package com.ecoSalud.springboot.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ecoSalud.springboot.app.models.entity.Doctor;
import com.ecoSalud.springboot.app.repository.DoctorRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor crear(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> buscarTodo() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    public Doctor buscarPorId(Integer id) {
        return doctorRepository.findById(id).get();
    }

    public Doctor actualizar(Doctor doctorActualizar) {
        Doctor doctorActual = doctorRepository.findById(doctorActualizar.getId_doctor()).get();
        
        doctorActual.setId_doctor(doctorActualizar.getId_doctor());
        doctorActual.setNombres(doctorActualizar.getNombres());
        doctorActual.setApellidos(doctorActualizar.getApellidos());
        doctorActual.setDisponibilidad(doctorActualizar.getDisponibilidad());
        doctorActual.setEspecialidad(doctorActualizar.getEspecialidad());
        
        Doctor doctorActualizado = doctorRepository.save(doctorActual);
        return doctorRepository.save(doctorActual);
    }

    public void eliminarDoctor(Integer id) {
        doctorRepository.deleteById(id);
    }
}
