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

import com.ecoSalud.springboot.app.models.entity.Horario;
import com.ecoSalud.springboot.app.services.HorarioService;

@Controller
@RequestMapping("/horario")
public class HorarioController {
    @Autowired
    private HorarioService horarioService;

    @RequestMapping("/listar")
    public String listar(Model model) {
        List<Horario> listaHorarios = horarioService.buscarTodo();
        model.addAttribute("listaHorarios", listaHorarios);
        return "moduloHorario/listar";
    }

    @RequestMapping("/crear")
    public String crear(Model model) {
        Horario horario = new Horario();
        model.addAttribute("horario", horario);
        return "moduloHorario/crear";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String crear(@ModelAttribute("horario") @Valid Horario horario, BindingResult result) {
        if (result.hasErrors()) {
            return "moduloHorario/crear";
        }
        horarioService.crear(horario);
        return "redirect:/horario/listar";
    }

    @RequestMapping(value = "/actualizar/{id}", method = RequestMethod.GET)
    public ModelAndView editar(@PathVariable(name = "id") Integer id) {
        ModelAndView mav = new ModelAndView("moduloHorario/form");
        Horario horario = horarioService.buscarPorId(id);
        mav.addObject("horario", horario);
        return mav;
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
    public String eliminar(@PathVariable(name = "id") Integer id) {
        horarioService.eliminarHorario(id);
        return "redirect:/horario/listar";
    }
}
