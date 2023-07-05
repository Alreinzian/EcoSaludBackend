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

import com.ecoSalud.springboot.app.models.entity.Historial;
import com.ecoSalud.springboot.app.services.HistorialService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/rest/historial")
public class HistorialRestController {

    @Autowired
    private HistorialService historialService;

    @GetMapping
    public ResponseEntity<Object> buscarTodo() {
        List<Historial> listaHistoriales = historialService.buscarTodo();
        return new ResponseEntity<>(listaHistoriales, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ResponseEntity<Object> buscarPorId(@PathVariable("id") Integer id) {
        Historial historial = historialService.buscarPorId(id);
        if (historial == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Historial no encontrado, el ID proporcionado no es correcto");
        return new ResponseEntity<Object>(historial, HttpStatus.OK);
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> crear(@RequestBody Historial historial) {
        Historial nuevoHistorial = historialService.crear(historial);
        return new ResponseEntity<Object>(nuevoHistorial, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Object> actualizar(@PathVariable("id") Integer id, @RequestBody Historial historial) {
        Historial historialExistente = historialService.buscarPorId(id);
        if (historialExistente == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Historial no encontrado, el ID proporcionado no es correcto");

        historial.setId_historial(id);
        Historial historialActualizado = historialService.actualizar(historial);
        return new ResponseEntity<Object>(historialActualizado, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
        Historial historialExistente = historialService.buscarPorId(id);
        if (historialExistente == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Historial no encontrado, el ID proporcionado no es correcto");

        historialService.eliminarHistorial(id);
        return new ResponseEntity<Object>("Historial eliminado correctamente", HttpStatus.OK);
    }
}
