package com.demo.FactoryMethodLogisticaTransporte.controller;

import com.demo.FactoryMethodLogisticaTransporte.logistica.LogisticaTierra;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogisticaTierraController {
    LogisticaTierra logisticaTierra = new LogisticaTierra();
    @GetMapping("/logisticaTierra")
    public String logisticaMarina() {
        // traer ruta de entrega
        logisticaTierra.planEntrega();
        return "Logistica tierra";
    }
}
