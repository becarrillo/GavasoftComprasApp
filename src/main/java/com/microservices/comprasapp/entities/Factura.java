package com.microservices.comprasapp.entities;

import com.microservices.comprasapp.external.models.Agendamiento;
import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

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

    @Column(name = "cliente_tipo_documento")
    private String clienteTipoDocumento;

    @Column(name = "cliente_num_documento")
    private String clienteNumDocumento;

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

    // Anotación '@Transient': El atributo es de valor no persistido en base de datos
    @Transient
    private List<Agendamiento> agendamientosList;

    public Factura(
            String clienteTipoDocumento,
            String clienteNumDocumento,
            String carritoDeComprasId,
            List<Agendamiento> agendamientosList,
            String metodoDePago
    ) {
        this.clienteTipoDocumento = clienteTipoDocumento;
        this.clienteNumDocumento = clienteNumDocumento;
        this.carritoDeComprasId = carritoDeComprasId;
        this.agendamientosList = agendamientosList;
        this.metodoDePago = metodoDePago;
        this.fechaHora = LocalDateTime.now(ZoneId.of("GMT-5"));
    }
}
