package com.ecoSalud.springboot.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ecoSalud.springboot.app.models.entity.Doctor;
import com.ecoSalud.springboot.app.services.DoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	@Autowired
	private DoctorService doctorService;

	@RequestMapping("/listar")
	public String listar(Model model) {

		List<Doctor> listaDoctores = doctorService.buscarTodo();
		System.out.println("LISTA DE DOCTORES: " + listaDoctores);
		model.addAttribute("listaDoctores", listaDoctores);
		return "moduloDoctor/listar";
	}

	@RequestMapping("/crear")
	public String crear(Model model) {
		Doctor doctor = new Doctor();
		model.addAttribute("doctor", doctor);
		return "moduloDoctor/crear";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String crear(@ModelAttribute("doctor") Doctor doctor) {
		doctorService.crear(doctor);
		return "redirect:/doctor/listar";
	}

	@RequestMapping(value = "/actualizar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable(name = "id") Integer id) {
		ModelAndView mav = new ModelAndView("moduloDoctor/form");
		Doctor doctor = doctorService.buscarPorId(id);
		mav.addObject("doctor", doctor);
		return mav;
	}

	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
	public String eliminar(@PathVariable(name = "id") Integer id) {
		doctorService.eliminarDoctor(id);
		return "redirect:/doctor/listar";
	}

}
