package com.ecoSalud.springboot.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecoSalud.springboot.app.models.entity.Cita;
import com.ecoSalud.springboot.app.models.entity.Doctor;
import com.ecoSalud.springboot.app.models.entity.Usuario;
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
        return citaRepository.findById(id).orElse(null);
    }

    
    public Cita actualizar(Cita citaActualizar) {
        Cita citaActual = citaRepository.findById(citaActualizar.getIdCita()).orElse(null);

        if (citaActual != null) {
            citaActual.setIdCita(citaActualizar.getIdCita());
            citaActual.setFecha(citaActualizar.getFecha());
            citaActual.setHora(citaActualizar.getHora());
            citaActual.setUbicacion(citaActualizar.getUbicacion());
            citaActual.setEstadoCita(citaActualizar.getEstadoCita());
            citaActual.setDiagnostico(citaActualizar.getDiagnostico());

            // Verificar si se proporciona un usuario válido
            Usuario usuario = citaActualizar.getUsuario();
            if (usuario != null) {
                citaActual.setUsuario(usuario);
            }

            // Verificar si se proporciona un doctor válido
            Doctor doctor = citaActualizar.getDoctor();
            if (doctor != null) {
                citaActual.setDoctor(doctor);
            }

            return citaRepository.save(citaActual);
        } else {
            return null;
        }
    }

    public void eliminarCita(Integer id) {
        citaRepository.deleteById(id);
    }
}
