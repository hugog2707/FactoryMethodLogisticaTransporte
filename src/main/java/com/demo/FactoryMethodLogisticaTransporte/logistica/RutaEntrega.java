package com.demo.FactoryMethodLogisticaTransporte.logistica;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.GraphHopperConfig;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.util.shapes.GHPoint;
import lombok.Data;
import org.hibernate.graph.Graph;

import java.io.File;
import java.io.InputStream;
import java.util.Objects;

@Data
public class RutaEntrega {
    private String origen;
    private String destino;
    private long distancia;
    private long tiempo;
    private GraphHopper hopper;

    public RutaEntrega() {
        // Constructor vacío
    }

    public void inicializarGraphHopper() {
        hopper = new GraphHopperOSM().forServer();
        hopper.setDataReaderFile("./src/main/resources/colombia-latest.osm.pbf");
        hopper.setGraphHopperLocation("./out/colombia-latest-gh");
        hopper.setEncodingManager(EncodingManager.create("car"));

        hopper.importOrLoad();
    }



    public double calcularDistancia() {
        GHPoint puntoOrigen = GHPoint.fromString(origen);
        GHPoint puntoDestino = GHPoint.fromString(destino);

        GHRequest request = new GHRequest(puntoOrigen, puntoDestino);
        GHResponse response = hopper.route(request);
        if (response.hasErrors()) {
            System.out.println("Error al calcular la ruta de entrega: " + response.getErrors().get(0).getMessage());
            return 0;
        }
        return response.getBest().getDistance();
    }
}
