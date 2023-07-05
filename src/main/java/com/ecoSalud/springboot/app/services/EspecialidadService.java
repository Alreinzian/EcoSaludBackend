package com.ecoSalud.springboot.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecoSalud.springboot.app.models.entity.Especialidad;
import com.ecoSalud.springboot.app.repository.EspecialidadRepository;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public Especialidad crear(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    public List<Especialidad> buscarTodo() {
        return (List<Especialidad>) especialidadRepository.findAll();
    }

    public Especialidad buscarPorId(Integer id) {
        return especialidadRepository.findById(id).orElse(null);
    }

    public Especialidad actualizar(Especialidad especialidadActualizar) {
        Especialidad especialidadActual = especialidadRepository.findById(especialidadActualizar.getId_especialidad()).orElse(null);

        if (especialidadActual != null) {
            especialidadActual.setEspecialidad(especialidadActualizar.getEspecialidad());
            especialidadActual.setId_doctor(especialidadActualizar.getId_doctor());

            return especialidadRepository.save(especialidadActual);
        } else {
            return null;
        }
    }

    public void eliminarEspecialidad(Integer id) {
        especialidadRepository.deleteById(id);
    }
}
