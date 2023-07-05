package com.ecoSalud.springboot.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecoSalud.springboot.app.models.entity.Horario;
import com.ecoSalud.springboot.app.repository.HorarioRepository;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public Horario crear(Horario horario) {
        return horarioRepository.save(horario);
    }

    public List<Horario> buscarTodo() {
        return (List<Horario>) horarioRepository.findAll();
    }

    public Horario buscarPorId(Integer id) {
        return horarioRepository.findById(id).orElse(null);
    }

    public Horario actualizar(Horario horarioActualizar) {
        Horario horarioActual = horarioRepository.findById(horarioActualizar.getId_horario()).orElse(null);

        if (horarioActual != null) {
            horarioActual.setId_doctor(horarioActualizar.getId_doctor());
            horarioActual.setDia(horarioActualizar.getDia());
            horarioActual.setHorario(horarioActualizar.getHorario());
            horarioActual.setHorarioinicio(horarioActualizar.getHorarioinicio());
            horarioActual.setHorariofin(horarioActualizar.getHorariofin());
           

            return horarioRepository.save(horarioActual);
        } else {
            return null;
        }
    }

    public void eliminarHorario(Integer id) {
        horarioRepository.deleteById(id);
    }
}
