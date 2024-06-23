package com.microservices.comprasapp.entities;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @author Brando Elí Carrillo Pérez
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "facturas")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Unsigned
    @Column(name = "factura_id")
    private int facturaId;

    @Column(name = "cliente_num_documento")
    private String clienteNumDocumento;

    @Column(name = "concepto_de_pago")
    private String conceptoDePago;

    @Column(name = "iva")
    private Short iva;

    @Column(name = "total")
    private int total;

    @Column(name = "estado_de_pago")
    private byte estadoDePago = 0;  // Por defecto es equivalente a un false (NO PAGO)

    @Column(name = "fecha_de_pago")
    private Date fechaDePago;

    @Column(name = "metodo_de_pago")
    private String metodoDePago;

    @Column(name = "carrito_de_compras_id")
    private String carritoDeComprasId;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    public Factura(
            String clienteNumDocumento,
            String conceptoDePago,
            String metodoDePago
    ) {
        this.clienteNumDocumento = clienteNumDocumento;
        this.conceptoDePago = conceptoDePago;
        this.metodoDePago = metodoDePago;
    }

    public Factura(
            String clienteNumDocumento,
            String conceptoDePago,
            String carritoDeComprasId,
            String metodoDePago
    ) {
        this.clienteNumDocumento = clienteNumDocumento;
        this.conceptoDePago = conceptoDePago;
        this.carritoDeComprasId = carritoDeComprasId;
        this.metodoDePago = metodoDePago;
    }
}
