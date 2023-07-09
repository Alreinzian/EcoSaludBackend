package com.ecoSalud.springboot.app.integration.firebase.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Scope("session")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    
    @Builder.Default
    private boolean activeSession = false;

    private String uid;
    private String email;
    private String displayName;
    private String idToken;


    public void reset() {
        this.activeSession = false;
        this.uid = null;
        this.email = null;
        this.displayName = null;
        this.idToken = null;
    }

}