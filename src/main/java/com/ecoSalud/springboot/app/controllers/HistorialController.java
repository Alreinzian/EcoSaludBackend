package com.ecoSalud.springboot.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ecoSalud.springboot.app.models.entity.Historial;
import com.ecoSalud.springboot.app.services.HistorialService;

@Controller
@RequestMapping("/historial")
public class HistorialController {
    @Autowired
    private HistorialService historialService;

    @RequestMapping("/listar")
    public String listar(Model model) {
        List<Historial> listaHistoriales = historialService.buscarTodo();
        model.addAttribute("listaHistoriales", listaHistoriales);
        return "moduloHistorial/listar";
    }

    @RequestMapping("/crear")
    public String crear(Model model) {
        Historial historial = new Historial();
        model.addAttribute("historial", historial);
        return "moduloHistorial/crear";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String crear(@ModelAttribute("historial") @Valid Historial historial, BindingResult result) {
        if (result.hasErrors()) {
            return "moduloHistorial/crear";
        }
        historialService.crear(historial);
        return "redirect:/historial/listar";
    }

    @RequestMapping(value = "/actualizar/{id}", method = RequestMethod.GET)
    public ModelAndView editar(@PathVariable(name = "id") Integer id) {
        ModelAndView mav = new ModelAndView("moduloHistorial/form");
        Historial historial = historialService.buscarPorId(id);
        mav.addObject("historial", historial);
        return mav;
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
    public String eliminar(@PathVariable(name = "id") Integer id) {
        historialService.eliminarHistorial(id);
        return "redirect:/historial/listar";
    }
}
