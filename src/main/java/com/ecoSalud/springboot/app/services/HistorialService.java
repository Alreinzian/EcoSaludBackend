package com.ecoSalud.springboot.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecoSalud.springboot.app.models.entity.Historial;
import com.ecoSalud.springboot.app.repository.HistorialRepository;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HistorialService {

    @Autowired
    private HistorialRepository historialRepository;

    public Historial crear(Historial historial) {
        return historialRepository.save(historial);
    }

    public List<Historial> buscarTodo() {
        return (List<Historial>) historialRepository.findAll();
    }

    public Historial buscarPorId(Integer id) {
        return historialRepository.findById(id).orElse(null);
    }

    public Historial actualizar(Historial historialActualizar) {
        Historial historialActual = historialRepository.findById(historialActualizar.getId_historial()).orElse(null);

        if (historialActual != null) {
            historialActual.setId_usuario(historialActualizar.getId_usuario());
            historialActual.setId_doctor(historialActualizar.getId_doctor());
            historialActual.setFecha_registro(historialActualizar.getFecha_registro());
            historialActual.setDiagnostico(historialActualizar.getDiagnostico());
            historialActual.setTratamiento(historialActualizar.getTratamiento());

            return historialRepository.save(historialActual);
        } else {
            return null;
        }
    }

    public void eliminarHistorial(Integer id) {
        historialRepository.deleteById(id);
    }
}
