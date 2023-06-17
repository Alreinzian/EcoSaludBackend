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

import com.ecoSalud.springboot.app.models.entity.Doctor;
import com.ecoSalud.springboot.app.services.DoctorService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/rest/doctor")
public class DoctorRestController {

    @Autowired
    private DoctorService servicio;

    @GetMapping
    public ResponseEntity<Object> buscarTodo() {
        List<Doctor> listaDoctores = servicio.buscarTodo();
        System.out.println("LISTA DE DOCTORES: " + listaDoctores);
        return new ResponseEntity<>(listaDoctores, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ResponseEntity<Object> buscarPorId(@PathVariable("id") int id) {
        Doctor doctor = servicio.buscarPorId(id);
        if (doctor == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Doctor no encontrado, el ID proporcionado no es correcto");
        return new ResponseEntity<Object>(doctor, HttpStatus.OK);
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> crear(@RequestBody Doctor doctor) {
        servicio.crear(doctor);
        return new ResponseEntity<Object>("Doctor creado correctamente", HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> actualizar(@PathVariable("id") int id, @RequestBody Doctor doctor) {

        servicio.actualizar(doctor);
        return new ResponseEntity<Object>("Doctor actualizado correctamente", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") int id) {
        servicio.eliminarDoctor(id);
        return new ResponseEntity<Object>("Doctor eliminado correctamente", HttpStatus.OK);
    }
}

