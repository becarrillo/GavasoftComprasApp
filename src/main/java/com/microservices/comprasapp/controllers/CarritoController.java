package com.microservices.comprasapp.controllers;

import com.microservices.comprasapp.entities.Carrito;
import com.microservices.comprasapp.services.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/carritos-de-compras")
public class CarritoController {
    @Autowired
    private CarritoService carritoService;

    @PostMapping(path = "/crear/nuevo")
    public String crear(@RequestBody Carrito carrito) {
        return carritoService.create(carrito);
    }

    @PostMapping(path = "/{carritoDeComprasId}/agregar/item")
    public String sumarSubtotalPorAgendamiento(
            @PathVariable("carritoDeComprasId") String carritoId,
            @RequestBody int agendamientoSubtotal
    ) {
        Carrito foundCarrito;
        foundCarrito = carritoService.getOne(carritoId);
        carritoService.addAgendamientoSubtotal(foundCarrito, agendamientoSubtotal);
        return carritoService.updateOneById(foundCarrito.getCarritoId(), foundCarrito).toString();
    }

    @GetMapping(path = "/{carritoDeComprasId}")
    public Carrito consultar(@PathVariable("carritoDeComprasId") String carritoId) {
        return carritoService.getOne(carritoId);
    }

    @DeleteMapping(path = "/{carritoDeComprasId}/cancelar")
    public void cancelar(@PathVariable("carritoDeComprasId") String carritoDeComprasId) {
        carritoService.deleteOneById(carritoDeComprasId);
    }
}
