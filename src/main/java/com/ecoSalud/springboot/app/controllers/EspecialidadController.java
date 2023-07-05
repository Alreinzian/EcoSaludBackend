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

import com.ecoSalud.springboot.app.models.entity.Especialidad;
import com.ecoSalud.springboot.app.services.EspecialidadService;

@Controller
@RequestMapping("/especialidad")
public class EspecialidadController {
    @Autowired
    private EspecialidadService especialidadService;

    @RequestMapping("/listar")
    public String listar(Model model) {
        List<Especialidad> listaEspecialidades = especialidadService.buscarTodo();
        model.addAttribute("listaEspecialidades", listaEspecialidades);
        return "moduloEspecialidad/listar";
    }

    @RequestMapping("/crear")
    public String crear(Model model) {
        Especialidad especialidad = new Especialidad();
        model.addAttribute("especialidad", especialidad);
        return "moduloEspecialidad/crear";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String crear(@ModelAttribute("especialidad") @Valid Especialidad especialidad, BindingResult result) {
        if (result.hasErrors()) {
            return "moduloEspecialidad/crear";
        }
        especialidadService.crear(especialidad);
        return "redirect:/especialidad/listar";
    }

    @RequestMapping(value = "/actualizar/{id}", method = RequestMethod.GET)
    public ModelAndView editar(@PathVariable(name = "id") Integer id) {
        ModelAndView mav = new ModelAndView("moduloEspecialidad/form");
        Especialidad especialidad = especialidadService.buscarPorId(id);
        mav.addObject("especialidad", especialidad);
        return mav;
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
    public String eliminar(@PathVariable(name = "id") Integer id) {
        especialidadService.eliminarEspecialidad(id);
        return "redirect:/especialidad/listar";
    }
}

