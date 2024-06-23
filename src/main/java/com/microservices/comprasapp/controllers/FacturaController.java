package com.microservices.comprasapp.controllers;

import com.microservices.comprasapp.entities.Factura;
import com.microservices.comprasapp.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://USUARIO-APP")
@RequestMapping(path = "/v1/facturas")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @PostMapping(path = "/generar-nueva")
    public Factura generar(@RequestBody Factura factura) {
        Factura savedFactura;
        try {
            savedFactura = facturaService.save(factura);
        } catch (Exception e) {
            savedFactura = null;
        }
        return savedFactura;
    }

    @GetMapping(path = "/clientes/{numDocumento}/pagadas")
    public List<Factura> obtenerPagadasPorUsuarioClienteId(
            @PathVariable("numDocumento") String clienteNumDocumento
    ) {
        return facturaService.getAllPagadasByClienteNumDocumento(
                clienteNumDocumento
        );
    }
}
