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

import com.ecoSalud.springboot.app.models.entity.Doctor;
import com.ecoSalud.springboot.app.models.entity.Usuario;
import com.ecoSalud.springboot.app.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("/listar")
    public String listar(Model model) {

        List<Usuario> listaUsuarios = usuarioService.buscarTodo();
        System.out.println("LISTA DE USUARIOS: " + listaUsuarios);
        model.addAttribute("listaUsuarios", listaUsuarios);
        return "moduloUsuario/listar";
    }

    @RequestMapping("/crear")
    public String crear(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "moduloUsuario/crear";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String crear(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.crear(usuario);
        return "redirect:/usuario/listar";
    }

    @RequestMapping(value = "/actualizar/{id}", method = RequestMethod.GET)
    public ModelAndView editar(@PathVariable(name = "id") Integer id) {
        ModelAndView mav = new ModelAndView("moduloUsuario/form");
        Usuario usuario = usuarioService.buscarPorId(id);
        mav.addObject("usuario", usuario);
        return mav;
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
    public String eliminar(@PathVariable(name = "id") Integer id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuario/listar";
    }
}