package com.demo.FactoryMethodLogisticaTransporte.logistica;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.config.Profile;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.util.shapes.GHPoint;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class RutaEntrega {
    private String origen;
    private String destino;
    public double distancia;
    public long horas;
    public long minutos;
    public long segundos;
    public long milisegundos;
    public String tiempoFinal;
    private String desFinal;
    private static String tipoTransporte;
    private static GraphHopper hopper;
    protected String apikey = "YOUR_API_KEY";
    // instance logger
    private final Logger logger = LoggerFactory.getLogger(getClass());

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

    public String setOrigen(String origen) {
        // Implementación específica para logística terrestre
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(apikey);

        JOpenCageForwardRequest request = new JOpenCageForwardRequest(origen);

        request.setMinConfidence(1);
        request.setNoAnnotations(false);
        request.setNoDedupe(true);

        JOpenCageResponse response = jOpenCageGeocoder.forward(request);

        desFinal = response.getResults().get(0).getGeometry().getLat().toString() + "," + response.getResults().get(0).getGeometry().getLng().toString();

        return desFinal;
    }

    public String setDestino(String destino) {
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(apikey);

        JOpenCageForwardRequest request = new JOpenCageForwardRequest(destino);

        request.setMinConfidence(1);
        request.setNoAnnotations(false);
        request.setNoDedupe(true);

        JOpenCageResponse response = jOpenCageGeocoder.forward(request);

        desFinal = response.getResults().get(0).getGeometry().getLat().toString() + "," + response.getResults().get(0).getGeometry().getLng().toString();

        return desFinal;
    }


    public double calcularDistancia(String origen, String destino) {
        this.origen = setOrigen(origen);
        this.destino = setDestino(destino);

        GHPoint puntoOrigen = GHPoint.fromString(this.origen);
        GHPoint puntoDestino = GHPoint.fromString(this.destino);

        GHRequest request = new GHRequest(puntoOrigen, puntoDestino)
                .setProfile(tipoTransporte);
        GHResponse response = hopper.route(request);

        distancia = response.getBest().getDistance();
        milisegundos = response.getBest().getTime();

        if (response.hasErrors()) {
            logger.error("Error al calcular la ruta de entrega: " + response.getErrors().get(0).getMessage());
            return 0;
        }

        return response.getBest().getDistance();
    }

    public String tiempoToString(){
        segundos = milisegundos / 1000;
        horas = segundos / 3600;
        minutos = (segundos % 3600) / 60;
        tiempoFinal = "Tiempo de entrega: " + horas + " horas" + " " + minutos + " minutos" + " " + (segundos % 60) + " segundos";

        return tiempoFinal;
    }

}
