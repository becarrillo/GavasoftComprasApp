package com.microservices.comprasapp.external.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Agendamiento {
    private String agendamientoId;
    private LocalDateTime fechaHora;
    private String servicioId;
    private String estado;
    private Short usuarioClienteId;
    private String carritoDeComprasId;
}
