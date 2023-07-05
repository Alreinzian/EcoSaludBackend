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

import com.ecoSalud.springboot.app.models.entity.Especialidad;
import com.ecoSalud.springboot.app.services.EspecialidadService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/rest/especialidad")
public class EspecialidadRestController {

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping
    public ResponseEntity<Object> buscarTodo() {
        List<Especialidad> listaEspecialidades = especialidadService.buscarTodo();
        return new ResponseEntity<>(listaEspecialidades, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ResponseEntity<Object> buscarPorId(@PathVariable("id") Integer id) {
        Especialidad especialidad = especialidadService.buscarPorId(id);
        if (especialidad == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Especialidad no encontrada, el ID proporcionado no es correcto");
        return new ResponseEntity<Object>(especialidad, HttpStatus.OK);
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> crear(@RequestBody Especialidad especialidad) {
        Especialidad nuevaEspecialidad = especialidadService.crear(especialidad);
        return new ResponseEntity<Object>(nuevaEspecialidad, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> actualizar(@PathVariable("id") Integer id, @RequestBody Especialidad especialidad) {
        Especialidad especialidadExistente = especialidadService.buscarPorId(id);
        if (especialidadExistente == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Especialidad no encontrada, el ID proporcionado no es correcto");

        especialidad.setId_especialidad(id);
        Especialidad especialidadActualizada = especialidadService.actualizar(especialidad);
        return new ResponseEntity<Object>(especialidadActualizada, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
        Especialidad especialidadExistente = especialidadService.buscarPorId(id);
        if (especialidadExistente == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Especialidad no encontrada, el ID proporcionado no es correcto");

        especialidadService.eliminarEspecialidad(id);
        return new ResponseEntity<Object>("Especialidad eliminada correctamente", HttpStatus.OK);
    }
}

