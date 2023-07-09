package com.ecoSalud.springboot.app.integration.firebase.service;

import org.springframework.http.HttpStatus;

import com.ecoSalud.springboot.app.integration.firebase.models.RequestAuthDto;


public interface authService {

    public HttpStatus signIn( RequestAuthDto req );
    public HttpStatus login( RequestAuthDto req );
    public HttpStatus checkSession();
    public HttpStatus closeSession();

}
