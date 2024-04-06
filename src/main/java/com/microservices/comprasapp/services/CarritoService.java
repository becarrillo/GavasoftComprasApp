package com.microservices.comprasapp.services;

import com.microservices.comprasapp.entities.Carrito;
import com.microservices.comprasapp.repositories.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Brando Elí Carrillo Pérez
 */
@Service
public class CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;

    public void addAgendamientoSubtotal(Carrito carrito, int agendamientoSubtotal) {
        final int suma = carrito.getSubtotal() + agendamientoSubtotal;
        carrito.setSubtotal(suma);
        // Actualización del carrito de compras con la suma del precio de nuevo servicio agendado
        carritoRepository.save(carrito);
    }

    public String create(Carrito carrito) {
        return carritoRepository.save(carrito).getCarritoId();
    }

    public void deleteOneById(String carritoId) {
        final Optional<Carrito> optCarrito = carritoRepository.findById(carritoId);
        assert optCarrito.orElse(null) != null;
        carritoRepository.delete(optCarrito.orElse(null));
    }

    public Carrito getOne(String carritoDeComprasId) {
        final Optional<Carrito> optCarrito = carritoRepository.findById(carritoDeComprasId);
        return optCarrito.orElse(null);
    }

    public int getSubtotalById(String carritoDeComprasId) {
        return Objects.requireNonNull(carritoRepository.findAll()
                        .stream().filter(c -> c.getCarritoId().equals(carritoDeComprasId)).findAny()
                        .orElse(null))
                .getSubtotal();
    }

    public Carrito updateOneById(String carritoId, Carrito carrito) {
        final Optional<Carrito> optCarrito = carritoRepository.findById(carritoId);
        assert optCarrito.orElse(null) != null;
        carrito.setCarritoId(optCarrito.orElse(null).getCarritoId());
        return carritoRepository.save(carrito);
    }
}
