package com.ecoSalud.springboot.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecoSalud.springboot.app.models.entity.Doctor;
import com.ecoSalud.springboot.app.repository.DoctorRepository;

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
        return doctorRepository.findById(id).orElse(null);
    }

    public Doctor actualizar(Doctor doctorActualizar) {
        Doctor doctorActual = doctorRepository.findById(doctorActualizar.getId_doctor()).orElse(null);
        
        if (doctorActual != null) {
            doctorActual.setNombres(doctorActualizar.getNombres());
            doctorActual.setApellidos(doctorActualizar.getApellidos());
            doctorActual.setDisponibilidad(doctorActualizar.getDisponibilidad());
            doctorActual.setEspecialidad(doctorActualizar.getEspecialidad());
            
            return doctorRepository.save(doctorActual);
        } else {
            return null;
        }
    }

    public void eliminarDoctor(Integer id) {
        doctorRepository.deleteById(id);
    }
}
