package com.ecoSalud.springboot.app.integration.firebase.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAuthDto {
    
    private int statusCode;
    private String uid;
    private String email;
    private String displayName;
    private String idToken;
    private String refreshToken;

}
