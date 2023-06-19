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

import com.ecoSalud.springboot.app.models.entity.Cita;
import com.ecoSalud.springboot.app.services.CitaService;

@Controller
@RequestMapping("/cita")
public class CitaController {
	@Autowired
	private CitaService citaService;

	@RequestMapping("/listar")
	public String listar(Model model) {
		List<Cita> listaCitas = citaService.buscarTodo();
		model.addAttribute("listaCitas", listaCitas);
		return "moduloCita/listar";
	}

	@RequestMapping("/crear")
	public String crear(Model model) {
		Cita cita = new Cita();
		model.addAttribute("cita", cita);
		return "moduloCita/crear";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String crear(@ModelAttribute("cita") @Valid Cita cita, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {

			return "moduloCita/crear";
		}
		citaService.crear(cita);
		return "redirect:/cita/listar";
	}

	@RequestMapping(value = "/actualizar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable(name = "id") Integer id) {
		ModelAndView mav = new ModelAndView("moduloCita/form");
		Cita cita = citaService.buscarPorId(id);
		mav.addObject("cita", cita);
		return mav;
	}

	@RequestMapping(value = "/actualizar/{id}", method = RequestMethod.POST)
	public String actualizar(@PathVariable(name = "id") Integer id, @ModelAttribute("cita") @Valid Cita cita,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {

			return "moduloCita/form";
		}
		citaService.actualizar(cita);
		return "redirect:/cita/listar";
	}

	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
	public String eliminar(@PathVariable(name = "id") Integer id) {
		citaService.eliminarCita(id);
		return "redirect:/cita/listar";
	}
}
