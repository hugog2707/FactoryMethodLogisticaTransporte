package com.demo.FactoryMethodLogisticaTransporte.controller;

import com.demo.FactoryMethodLogisticaTransporte.logistica.LogisticaTierra;
import com.demo.FactoryMethodLogisticaTransporte.logistica.RutaEntrega;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogisticaTierraController {
    LogisticaTierra logisticaTierra = new LogisticaTierra();
    @PostMapping("/logisticaTierra")
    //recibe un json con los datos de la ruta de entrega
    public String logisticaMarina(@RequestBody RutaEntrega rutaEntrega) {

        logisticaTierra.planEntrega(rutaEntrega.getOrigen(), rutaEntrega.getDestino());

        return logisticaTierra.distanciaToString();
    }
}
