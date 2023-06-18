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

import com.ecoSalud.springboot.app.models.entity.Cita;
import com.ecoSalud.springboot.app.services.CitaService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/rest/cita")
public class CitaRestController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public ResponseEntity<Object> buscarTodo() {
        List<Cita> listaCitas = citaService.buscarTodo();
        return new ResponseEntity<>(listaCitas, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ResponseEntity<Object> buscarPorId(@PathVariable("id") Integer id) {
        Cita cita = citaService.buscarPorId(id);
        if (cita == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Cita no encontrada, el ID proporcionado no es correcto");
        return new ResponseEntity<Object>(cita, HttpStatus.OK);
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> crear(@RequestBody Cita cita) {
        Cita nuevaCita = citaService.crear(cita);
        return new ResponseEntity<Object>(nuevaCita, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> actualizar(@PathVariable("id") Integer id, @RequestBody Cita cita) {
        Cita citaExistente = citaService.buscarPorId(id);
        if (citaExistente == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Cita no encontrada, el ID proporcionado no es correcto");

        cita.setIdCita(id);
        Cita citaActualizada = citaService.actualizar(cita);
        return new ResponseEntity<Object>(citaActualizada, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
        Cita citaExistente = citaService.buscarPorId(id);
        if (citaExistente == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Cita no encontrada, el ID proporcionado no es correcto");

        citaService.eliminarCita(id);
        return new ResponseEntity<Object>("Cita eliminada correctamente", HttpStatus.OK);
    }
}

