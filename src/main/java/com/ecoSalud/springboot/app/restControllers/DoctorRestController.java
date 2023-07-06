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
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<Object> buscarTodo() {
        List<Doctor> listaDoctores = doctorService.buscarTodo();
        return new ResponseEntity<>(listaDoctores, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ResponseEntity<Object> buscarPorId(@PathVariable("id") Integer id) {
        Doctor doctor = doctorService.buscarPorId(id);
        if (doctor == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Doctor no encontrado, el ID proporcionado no es correcto");
        return new ResponseEntity<Object>(doctor, HttpStatus.OK);
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> crear(@RequestBody Doctor doctor) {
        Doctor nuevoDoctor = doctorService.crear(doctor);
        return new ResponseEntity<Object>(nuevoDoctor, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> actualizar(@PathVariable("id") Integer id, @RequestBody Doctor doctor) {
        Doctor doctorExistente = doctorService.buscarPorId(id);
        if (doctorExistente == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Doctor no encontrado, el ID proporcionado no es correcto");

        doctor.setId_doctor(id);
        Doctor doctorActualizado = doctorService.actualizar(doctor);
        return new ResponseEntity<Object>(doctorActualizado, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
        Doctor doctorExistente = doctorService.buscarPorId(id);
        if (doctorExistente == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Doctor no encontrado, el ID proporcionado no es correcto");

        doctorService.eliminarDoctor(id);
        return new ResponseEntity<Object>("Doctor eliminado correctamente", HttpStatus.OK);
    }
}
