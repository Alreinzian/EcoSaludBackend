package com.ecoSalud.springboot.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecoSalud.springboot.app.models.entity.Cita;
import com.ecoSalud.springboot.app.models.entity.Doctor;
import com.ecoSalud.springboot.app.repository.CitaRepository;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public Cita crear(Cita cita) {
        return citaRepository.save(cita);
    }

    public List<Cita> buscarTodo() {
        return (List<Cita>) citaRepository.findAll();
    }

    public Cita buscarPorId(Integer id) {
        return citaRepository.findById(id).get();
    }

    public Cita actualizar(Cita citaActualizar) {
        Cita citaActual = citaRepository.findById(citaActualizar.getIdCita()).get();


            citaActual.setIdCita(citaActualizar.getIdCita());
            citaActual.setUsuario(citaActualizar.getUsuario());
            citaActual.setDoctor(citaActualizar.getDoctor());
            citaActual.setFecha(citaActualizar.getFecha());
            citaActual.setHora(citaActualizar.getHora());
            citaActual.setUbicacion(citaActualizar.getUbicacion());
            citaActual.setEstadoCita(citaActualizar.getEstadoCita());
            citaActual.setDiagnostico(citaActualizar.getDiagnostico());
            
            Cita citaActualizado = citaRepository.save(citaActual);
            return citaRepository.save(citaActual);
        
    }

    public void eliminarCita(Integer id) {
        citaRepository.deleteById(id);
    }
}
