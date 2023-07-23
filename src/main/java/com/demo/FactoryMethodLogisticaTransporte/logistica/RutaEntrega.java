package com.demo.FactoryMethodLogisticaTransporte.logistica;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.config.Profile;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.util.shapes.GHPoint;
import lombok.Data;

@Data
public class RutaEntrega {
    private String origen;
    private String destino;
    public double distancia;
    private static String tipoTransporte;
    private static GraphHopper hopper;

    public RutaEntrega() {
        // Constructor vacío
    }

    static void inicializarGraphHopper(String vehicle) {
        hopper = new GraphHopperOSM().forServer();

        hopper.setDataReaderFile("./src/main/resources/static/colombia-latest.osm.pbf");
        hopper.setGraphHopperLocation("./out/colombia-latest-gh");
        hopper.setProfiles(new Profile(vehicle).setVehicle(vehicle).setWeighting("fastest").setTurnCosts(false));
        hopper.importOrLoad();

        tipoTransporte = vehicle;
    }


    public double calcularDistancia(String origen, String destino) {
        GHPoint puntoOrigen = GHPoint.fromString(origen);
        GHPoint puntoDestino = GHPoint.fromString(destino);

        GHRequest request = new GHRequest(puntoOrigen, puntoDestino)
                .setProfile(tipoTransporte);
        GHResponse response = hopper.route(request);

        distancia = response.getBest().getDistance();

        if (response.hasErrors()) {
            System.out.println("Error al calcular la ruta de entrega: " + response.getErrors().get(0).getMessage());
            return 0;
        }

        return response.getBest().getDistance();
    }
}
