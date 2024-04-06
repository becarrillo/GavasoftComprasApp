package com.microservices.comprasapp.external.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ClienteService {
    @Autowired
    private RestTemplate restTemplate;

    public String getClienteNumDocumentoByUsuarioId(Short usuarioClienteId) {
        final String URL = "http://USUARIO-APP/usuarios/clientes/{usuarioClienteId}/numero-de-documento";
        return restTemplate.getForObject(URL,String.class,usuarioClienteId);
    }

    public String getClienteTipoDocumentoByUsuarioId(Short usuarioClienteId) {
        final String URL = "http://USUARIO-APP/usuarios/clientes/{usuarioClienteId}/tipo-de-documento";
        return restTemplate.getForObject(URL, String.class, usuarioClienteId);
    }

    public String getClienteApellidosByNumDocumento(String clienteNumDocumento) {
        String URL = "http://USUARIO-APP/usuarios/clientes/obtener-usuario-id/{clienteNumDocumento}";
        final Short usuarioId = restTemplate.getForObject(URL, Short.class, clienteNumDocumento);

        URL = "http://USUARIO-APP/usuarios/clientes/{usuarioId}/apellidos";
        return restTemplate.getForObject(URL, String.class, usuarioId);
    }

    public String getClienteNombreByNumDocumento(String clienteNumDocumento) {
        String URL = "http://USUARIO-APP/usuarios/clientes/obtener-usuario-id/{clienteNumDocumento}";
        final Short usuarioId = restTemplate.getForObject(URL, Short.class, clienteNumDocumento);

        URL = "http://USUARIO-APP/usuarios/clientes/{usuarioId}/nombre";
        return restTemplate.getForObject(URL, String.class, usuarioId);
    }
}
