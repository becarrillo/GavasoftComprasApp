package com.microservices.comprasapp.controllers;

import com.microservices.comprasapp.entities.Factura;
import com.microservices.comprasapp.external.models.Agendamiento;
import com.microservices.comprasapp.models.Ingreso;
import com.microservices.comprasapp.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/ingresos")
public class IngresoController {
    @Autowired
    private FacturaService facturaService;

    @GetMapping("clientes/{numDocumento}")
    public List<Ingreso> buscarIngresos(@PathVariable("numDocumento") String clienteNumDocumento) {
        final List<Factura> facturasList = facturaService
                .getAllPagadasByClienteNumDocumento(clienteNumDocumento);
        List<Agendamiento> fAgendamientos;
        final List<Ingreso> ingresosList = new ArrayList<Ingreso>();
        for (int i=0; i < facturasList.size(); i++) {
            fAgendamientos = facturasList.get(i).getAgendamientosList();
            for (int j=0; j < fAgendamientos.size(); j++) {
                // final Servicio servicio = servicioService.getOneById(fAgendamientos.get(j).getServicioId());
                final Ingreso ingreso;
                ingreso = new Ingreso(
                        facturasList.get(i).getFacturaId(),
                        fAgendamientos.get(j).getFechaHora(),
                        fAgendamientos.get(j).getServicioId()
                );
                ingresosList.add(ingreso);
            }
        }
        return ingresosList;
    }
}
