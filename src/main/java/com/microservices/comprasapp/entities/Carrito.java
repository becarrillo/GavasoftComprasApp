package com.microservices.comprasapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Brando Elí Carrillo Pérez
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "carritos_de_compras")
public class Carrito {
    @Id
    @Column(name = "carrito_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String carritoId;

    @Column(name = "subtotal")
    private int subtotal = 0;

    public Carrito(int subtotal) {
        this.subtotal = subtotal;
    }
}
