package com.microservices.comprasapp.services;

import com.microservices.comprasapp.entities.Factura;
import com.microservices.comprasapp.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Brando Elí Carrillo Pérez
 */
@Service
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;

    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    public List<Factura> getAllPagadasByClienteNumDocumento(String clienteNumDocumento) {
        final Optional<List<Factura>> optFacturas = Optional.of(facturaRepository.findAll()
                .stream()
                .filter(f -> f.getClienteNumDocumento().equals(clienteNumDocumento) && f.getEstadoDePago() == 1)
                .toList()
        );
        return optFacturas.orElse(null);
    }

    public List<Factura> getAllFacturasByFechas(Date fechaInicial, Date fechaFinal) {
        final Optional<List<Factura>> optFacturas = Optional.of(facturaRepository.findAll()
                        .stream()
                        .filter(f -> f.getEstadoDePago() == 1 && (
                                LocalDate.of(f.getFechaHora().getYear(), f.getFechaHora().getMonth(), f.getFechaHora().getDayOfMonth())
                                .isAfter(fechaInicial.toLocalDate()) && LocalDate.of(
                                        f.getFechaHora().getYear(), f.getFechaHora().getMonth(), f.getFechaHora().getDayOfMonth()
                                ).isBefore(fechaFinal.toLocalDate())  // Filtro de facturas hecho por pago verificado y por rango de fechas
                        )).toList()
        );
        return optFacturas.orElse(null);
    }

    /*public Factura toRegisterPayment() {

    }
     */
}
