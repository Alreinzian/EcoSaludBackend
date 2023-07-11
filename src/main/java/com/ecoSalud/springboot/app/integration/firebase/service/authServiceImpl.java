package com.ecoSalud.springboot.app.integration.firebase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

import com.ecoSalud.springboot.app.integration.firebase.models.RequestAuthDto;
import com.ecoSalud.springboot.app.integration.firebase.models.ResponseAuthDto;
import com.ecoSalud.springboot.app.integration.firebase.models.UserAccount;

import lombok.extern.slf4j.Slf4j;


@Service
@RequestScope
@Slf4j
public class authServiceImpl implements authService {


    @Autowired
    private UserAccount userAccount;

    @Value( "${custom.ms-firebase.url}" )
    private String firebaseUrl;

    @Override
    public HttpStatus signIn( RequestAuthDto req ) {
        
        try {

            ResponseEntity<ResponseAuthDto> registryData = new RestTemplate().exchange( firebaseUrl + "/signin", 
                HttpMethod.POST, 
                new HttpEntity<>( req ), 
                ResponseAuthDto.class );

            if ( registryData.getStatusCodeValue() != 200 ) {
                log.error( "Error al registrar" );
                return HttpStatus.BAD_REQUEST;
            }

            log.info("Usuario creado con éxito");
            return HttpStatus.OK;
            
            
        } catch( HttpClientErrorException.Conflict e ) {

            log.error( "Email en uso" );
            return HttpStatus.CONFLICT;

        } catch ( RestClientException e ) {

            log.error( "!! Fallo en el microservicio Firebase" );
            System.out.println( e.getMessage() );
            return HttpStatus.INTERNAL_SERVER_ERROR;

        } catch ( Exception e ) {

            log.error( "Fallo en el servidor", e );
            userAccount.reset();
            return HttpStatus.INTERNAL_SERVER_ERROR;

        }
    }



    @Override
    public HttpStatus login( RequestAuthDto req ){

        try {
            
            ResponseEntity<ResponseAuthDto> loginData = new RestTemplate().exchange( firebaseUrl + "/login", 
                HttpMethod.POST, 
                new HttpEntity<>( req ), 
                ResponseAuthDto.class );


            if ( loginData.getStatusCodeValue() != 200 ) {
                log.error( "Error al logear" );
                return HttpStatus.BAD_REQUEST;
            }

            userAccount.setActiveSession( true );
            userAccount.setUid( loginData.getBody().getUid() );
            userAccount.setEmail(loginData.getBody().getEmail() );
            userAccount.setDisplayName( loginData.getBody().getDisplayName() );
            userAccount.setIdToken( loginData.getBody().getIdToken() );

            log.info( "Usuario logeado con exito" );
            return HttpStatus.OK;

        } catch( HttpClientErrorException.BadRequest e ) {

            log.error( "Usuario/Contrasenia incorrectos" );
            return HttpStatus.BAD_REQUEST;

        } catch ( RestClientException e ) {

            log.error( "!! Fallo en el microservicio Firebase" );
            System.out.println( e.getMessage() );
            userAccount.reset();
            return HttpStatus.INTERNAL_SERVER_ERROR;

        } catch ( Exception e ) {

            log.error( "Fallo en el servidor", e );
            userAccount.reset();
            return HttpStatus.INTERNAL_SERVER_ERROR;

        }

    }


    @Override
    public HttpStatus checkSession() {

        try {
            
            if ( userAccount.getIdToken() == null ) {
                throw new IllegalArgumentException("El token es nulo");
            }

            RequestAuthDto user =  RequestAuthDto.builder().idToken( userAccount.getIdToken() ).build();

            ResponseEntity<ResponseAuthDto> loginData = new RestTemplate().exchange( firebaseUrl + "/checkSession", 
                HttpMethod.POST, 
                new HttpEntity<>( user ), 
                ResponseAuthDto.class );

            if ( loginData.getStatusCodeValue() == 400 ) {
                log.error( "No existe una sesión activa" );
                return HttpStatus.BAD_REQUEST;
            }

            if ( loginData.getStatusCodeValue() != 200 ) {
                log.error( "Error al verificar la sesion" );
                return HttpStatus.BAD_REQUEST;
            }

            log.info( "Sesion verificada" );
            return HttpStatus.OK;
            
        } catch( HttpClientErrorException.BadRequest e ) {

            log.error( "No existe una sesion activa" );
            return HttpStatus.BAD_REQUEST;

        } catch ( RestClientException e ) {

            log.error( "!! Fallo en el microservicio Firebase" );
            System.out.println( e.getMessage() );
            return HttpStatus.INTERNAL_SERVER_ERROR;

        } catch ( Exception e ) {
            
            log.error( "Fallo en el servidor", e );
            return HttpStatus.INTERNAL_SERVER_ERROR;

        }

    }



    @Override
    public HttpStatus closeSession(){

        try {

            RequestAuthDto user =  RequestAuthDto.builder()
                .uid( userAccount.getUid() )
                .idToken( userAccount.getIdToken() )
                .build();

            ResponseEntity<ResponseAuthDto> closeSession = new RestTemplate().exchange( firebaseUrl + "/closeSession", 
                HttpMethod.POST, 
                new HttpEntity<>( user ), 
                ResponseAuthDto.class );

            if ( closeSession.getStatusCodeValue() != 200 ) {
                log.error( "Error al cerrar la sesion" );
                userAccount.reset();
                return HttpStatus.BAD_REQUEST;
            } 
            
            userAccount.reset();
            log.info( "Sesion cerrada" );
            return HttpStatus.OK;

        } catch( HttpClientErrorException.Unauthorized e ) {

            log.error( "El token no es valido" );
            return HttpStatus.UNAUTHORIZED;

        } catch( HttpClientErrorException.Forbidden e ) {

            log.error( "No tiene permiso para cerrar esta sesion" );
            return HttpStatus.FORBIDDEN;

        } catch ( RestClientException e ) {

            log.error( "!! Fallo en el microservicio Firebase" );
            System.out.println( e.getMessage() );
            return HttpStatus.INTERNAL_SERVER_ERROR;

        } catch ( Exception e ) {
            
            log.error( "Fallo en el servidor", e );
            return HttpStatus.INTERNAL_SERVER_ERROR;

        }
        
    }
    
}
