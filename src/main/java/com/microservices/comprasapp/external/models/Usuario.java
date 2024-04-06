package com.microservices.comprasapp.external.models;

import jdk.jfr.Unsigned;
import lombok.Getter;

@Getter
public class Usuario {
    @Unsigned
    private Short usuario_id;
    private String apellidos;
    private String nombre;
    private String email;
    private String password;
    private String rol;
    private String tel;
}
