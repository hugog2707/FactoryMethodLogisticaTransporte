package com.demo.FactoryMethodLogisticaTransporte.controller;

import com.demo.FactoryMethodLogisticaTransporte.logistica.LogisticaTierra;
import com.demo.FactoryMethodLogisticaTransporte.logistica.RutaEntrega;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/logisticaTierraApi")
@RestController
public class LogisticaTierraApiController {
    LogisticaTierra logisticaTierra = new LogisticaTierra();
    public String origen;
    public String destino;

    @PostMapping("/distanciaTiempo")
    //recibe un json con los datos de la ruta de entrega
    public String distanciaTiempo(@RequestBody RutaEntrega rutaEntrega){
        origen = rutaEntrega.getOrigen();
        destino = rutaEntrega.getDestino();

        logisticaTierra.planEntrega(origen, destino);

        return logisticaTierra.distanciaToString() + " " + logisticaTierra.tiempoToString();
    }
}
