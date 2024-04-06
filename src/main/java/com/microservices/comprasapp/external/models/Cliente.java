package com.microservices.comprasapp.external.models;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Cliente extends Usuario {
    private String tipo_documento;
    private String num_documento;
    private boolean autorizacion_datos;
    private int saldo_favor;
    private LocalDateTime fecha_hora_registro;
}
