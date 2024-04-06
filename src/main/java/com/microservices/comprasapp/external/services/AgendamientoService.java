package com.microservices.comprasapp.external.services;

import com.microservices.comprasapp.external.models.Agendamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamientoService {
    @Autowired
    private RestTemplate restTemplate;

    public List<Agendamiento> listAllByCarritoDeComprasId(String carritoDeComprasId) {
        final String URL = "http://AGENDAMIENTO-APP/agendamientos/carritos-de-compras/{carritoDeComprasId}";
        final Optional<List<Agendamiento>> agendamientosList = Optional.of(restTemplate.getForObject(URL, List.class, carritoDeComprasId));
        return agendamientosList.orElse(null);
    }

    public List<Agendamiento> listAllByCarritoDeComprasAndUsuarioClienteId(String carritoDeComprasId, Short usuarioClienteId) {
        final String URL = "http://AGENDAMIENTO-APP/agendamientos/carritos-de-compras/{carritoDeComprasId}";
        final List<Agendamiento> agendamientosList = restTemplate.getForObject(URL, List.class, carritoDeComprasId);
        assert agendamientosList != null;
        return agendamientosList
                .stream()
                .filter(a -> a.getUsuarioClienteId().equals(usuarioClienteId))
                .toList();
    }
}