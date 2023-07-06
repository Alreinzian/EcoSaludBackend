package com.ecoSalud.springboot.app.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecoSalud.springboot.app.models.entity.Horario;
import com.ecoSalud.springboot.app.services.HorarioService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/rest/horario")
public class HorarioRestController {

    @Autowired
    private HorarioService horarioService;

    @GetMapping
    public ResponseEntity<Object> buscarTodo() {
        List<Horario> listaHorarios = horarioService.buscarTodo();
        return new ResponseEntity<>(listaHorarios, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ResponseEntity<Object> buscarPorId(@PathVariable("id") Integer id) {
        Horario horario = horarioService.buscarPorId(id);
        if (horario == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Horario no encontrado, el ID proporcionado no es correcto");
        return new ResponseEntity<Object>(horario, HttpStatus.OK);
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> crear(@RequestBody Horario horario) {
        Horario nuevoHorario = horarioService.crear(horario);
        return new ResponseEntity<Object>(nuevoHorario, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> actualizar(@PathVariable("id") Integer id, @RequestBody Horario horario) {
        Horario horarioExistente = horarioService.buscarPorId(id);
        if (horarioExistente == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Horario no encontrado, el ID proporcionado no es correcto");

        horario.setId_horario(id);
        Horario horarioActualizado = horarioService.actualizar(horario);
        return new ResponseEntity<Object>(horarioActualizado, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
        Horario horarioExistente = horarioService.buscarPorId(id);
        if (horarioExistente == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Horario no encontrado, el ID proporcionado no es correcto");

        horarioService.eliminarHorario(id);
        return new ResponseEntity<Object>("Horario eliminado correctamente", HttpStatus.OK);
    }
}
