package com.ecoSalud.springboot.app.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecoSalud.springboot.app.models.entity.Doctor;
import com.ecoSalud.springboot.app.models.entity.Usuario;
import com.ecoSalud.springboot.app.services.DoctorService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/rest/doctor")
public class DoctorRestController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<Doctor>> buscarTodo() {
        List<Doctor> listaDoctores = doctorService.buscarTodo();
        return new ResponseEntity<>(listaDoctores, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Doctor> buscarPorId(@PathVariable("id") int id) {
        Doctor doctor = doctorService.buscarPorId(id);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<String> crear(@RequestBody Doctor doctor) {
        doctorService.crear(doctor);
        return new ResponseEntity<>("Doctor creado correctamente", HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> actualizar(@PathVariable("id") int id, @RequestBody Doctor doctor) {
        doctorService.actualizar(doctor);
        return new ResponseEntity<Object>("Doctor actualizado correctamente", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") int id) {
        doctorService.eliminarDoctor(id);
        return new ResponseEntity<Object>("Doctor eliminado correctamente", HttpStatus.OK);
    }
}

