package com.ecoSalud.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.RequestScope;

import com.ecoSalud.springboot.app.integration.firebase.models.RequestAuthDto;
import com.ecoSalud.springboot.app.integration.firebase.models.UserAccount;
import com.ecoSalud.springboot.app.integration.firebase.service.authService;

@Controller
@RequestScope // necesario para acceder al objeto scope ( userAccount )
public class ExampleAuthController {
    

    @Autowired
    private authService authService; // servicio de auth

    @Autowired
    private UserAccount userAccount; // objeto scope

    // ==========================================================
    // ===== VISTA DE PRUEBA
    // = http://localhost:8080/ecoSalud/init
    // para todas las rutas
    // ==========================================================

    @GetMapping( "/loginForm" )
    public String loginForm( Model model ) {

        return "tmp_login_juguete";
    }

    @GetMapping( "/init" )
    public String home( Model model ) {

        model.addAttribute("titulo", "Cuenta de usuario");

        // evaluar si existe una sesión activa
        if ( userAccount.isActiveSession() ) {

            // opcional: verificar si el token de sesion actal es valido
                HttpStatus responseStatus = authService.checkSession();

                if ( responseStatus == HttpStatus.OK ) {
                    // TOKEN VALIDADO
                }
            // ===========

            // otyros atributos
            model.addAttribute( "user", userAccount );

            // ruta a la que acceder
            return "tmp_view";
        }

        // redireccion a la vista login en caso de no tener unsa sesion activa
        return "redirect:/loginForm";

    }



    // ==========================================================
    // ===== LOGIN
    // = http://localhost:8080/ecoSalud/login
    // ==========================================================
    @GetMapping( "/login" )
    public String login( Model model ) {

        // Colocar los datos del formulario login
        RequestAuthDto requestBody = RequestAuthDto.builder()
            .email("iryps1502@gmail.com")
            .password("try1502")
            .build();

        HttpStatus responseStatus = authService.login( requestBody );

        
        if ( responseStatus == HttpStatus.BAD_REQUEST ) {
            System.out.println( "Email y/o contraseña incorrectos" );
        }

        if ( responseStatus == HttpStatus.INTERNAL_SERVER_ERROR ) {
            System.out.println( "Fallo en el servidor: error al loggear" );
        }

        // se supone que fue un 200 HttpStatus.OK, redirigiendo
        return "redirect:/init";
        
    }


    // ==========================================================
    // ===== REGISTRO
    // = http://localhost:8080/ecoSalud/register
    // ==========================================================
    @GetMapping( "/register" )
    public String register( Model model ) {

        // Colocar los datos de registro registro, el resto como DNI ETC VAN PARA PACIENTE
        RequestAuthDto requestBody = RequestAuthDto.builder()
            .email("iryps1502@gmail.com")
            .password("try1502")
            .displayName( "IRypS" )
            .build();

        HttpStatus responseStatus = authService.signIn( requestBody );

        
        if ( responseStatus == HttpStatus.CONFLICT ) {
            System.out.println( "Email en uso" );
        }

        if ( responseStatus == HttpStatus.BAD_REQUEST ) {
            System.out.println( "Fallo al registrar" );
        }

        if ( responseStatus == HttpStatus.INTERNAL_SERVER_ERROR ) {
            System.out.println( "Fallo en el servidor" );
        }

        // se supone que fue un 200, redirigiendo
        // solo se redirige al login, podrias realizar el post con el email y password anteriormente ingresados
        // nota: como mi login tiene datos en duro entonces automaticamente redirigira al init
        // puedes redirigir al form login 
        // (importante pasar por el servicio login pues este coloca los datos en el scope)
        return "redirect:/login";

    }
    

    // ==========================================================
    // ===== VERIFICAR SESIÓN POR IDTOKEN
    // = http://localhost:8080/ecoSalud/check
    // ==========================================================
    @GetMapping( "/check" )
    public String checkSession( Model model ) {

        HttpStatus responseStatus = authService.checkSession();
        
        if ( responseStatus == HttpStatus.BAD_REQUEST ) {
            System.out.println( "Fallo al verificar la sesión" );
        }
        

        if ( responseStatus == HttpStatus.INTERNAL_SERVER_ERROR ) {
            System.out.println( "Fallo en el servidor" );
        }

        // se supone que fue un 200, redirigiendo
        return "redirect:/init";

    }


    // ==========================================================
    // ===== CERRAR SESIÓN
    // = http://localhost:8080/ecoSalud/close
    // ==========================================================
    @GetMapping( "/close" )
    public String closeSession( Model model ) {

        HttpStatus responseStatus = authService.closeSession();

        
        if ( responseStatus == HttpStatus.UNAUTHORIZED ) {
            System.out.println( "Token inválido o expirado" );
        }

        if ( responseStatus == HttpStatus.FORBIDDEN ) {
            System.out.println( "El token y el uid actual no coinciden" );
        }

        if ( responseStatus == HttpStatus.BAD_REQUEST ) {
            System.out.println( "Fallo al cerrar la sesión" );
        }

        if ( responseStatus == HttpStatus.INTERNAL_SERVER_ERROR ) {
            System.out.println( "Error en el servidor" );
        }


        // se supone que fue un 200, redirigiendo
        return "redirect:/init";

    }


}
