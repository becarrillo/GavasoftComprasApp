package com.microservices.comprasapp.controllers;

import com.microservices.comprasapp.entities.Factura;
import com.microservices.comprasapp.external.models.Agendamiento;
import com.microservices.comprasapp.external.services.ClienteService;
import com.microservices.comprasapp.external.services.AgendamientoService;
import com.microservices.comprasapp.services.CarritoService;
import com.microservices.comprasapp.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@CrossOrigin("http://USUARIO-APP")
@RequestMapping(path = "/facturas")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AgendamientoService agendamientoService;

    @GetMapping(path = "usuarios/clientes/{clienteNumDocumento}")
    public List<Factura> buscarIngresoPorCliente(@PathVariable String clienteNumDocumento) {
        return facturaService.getAllPagadasByClienteNumDocumento(clienteNumDocumento);
    }

    @PostMapping(path = "/usuarios/clientes/{usuarioClienteId}/generar-nueva")
    public Factura generar(@PathVariable Short usuarioClienteId, @RequestBody Factura factura) {
        final List<Agendamiento> agendamientosListByCarrito = agendamientoService
                .listAllByCarritoDeComprasAndUsuarioClienteId(factura.getCarritoDeComprasId(), usuarioClienteId);
        if (!agendamientosListByCarrito.isEmpty()) {
            factura.setAgendamientosList(agendamientosListByCarrito);
            final String clienteNumDocumento = clienteService.getClienteNumDocumentoByUsuarioId(usuarioClienteId);
            factura.setClienteNumDocumento(clienteNumDocumento);
            factura.setClienteTipoDocumento(clienteService.getClienteTipoDocumentoByUsuarioId(usuarioClienteId));

            int subtotal;
            subtotal = carritoService.getSubtotalById(factura.getCarritoDeComprasId());
            factura.setFechaHora(LocalDateTime.now(ZoneId.of("GMT-5")));           // Timestamp (BOG)

            final Factura f = facturaService.save(factura);
            factura.setTotal(subtotal + (subtotal * f.getIva() / 100));
            // Por segunda vez, se guarda una instancia, pero ahora con el valor que la BD tiene de iva por defecto
            return facturaService.save(f);
        }
        return null;                        // No existe ningún agendamiento para facturar del carrito de compras
    }
}
