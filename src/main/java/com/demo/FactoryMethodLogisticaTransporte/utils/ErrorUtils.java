package com.demo.FactoryMethodLogisticaTransporte.utils;

import org.springframework.http.ResponseEntity;

public class ErrorUtils {
    public static String buildMessageError(String nameEntity, String action, Exception e) {
        return "Error al " + action + " " + nameEntity + ": " + e.getMessage();
    }

    public static ResponseEntity buildMessageDuplicateError(String nameEntity, String action, String entityName) {
        return ResponseEntity.status(409).body("Error al " + action + " " + nameEntity + ": " + "el " + entityName + " ya existe");
    }

    public static ResponseEntity buildMessageResponseError(String nameEntity, String action, Exception e) {
        return ResponseEntity.status(500).body("Error al " + action + " " + nameEntity + ": " + e.getMessage());
    }

    public static ResponseEntity buildMessageResponseNotFound(String nameEntity, String action, String entityName) {
        return ResponseEntity.status(404).body("Error al " + action + " " + nameEntity + ": " + "el " + entityName + " no existe");
    }

    public static ResponseEntity buildMessageResponseBadRequest(String nameEntity, String action, String entityName) {
        return ResponseEntity.status(400).body("Error al " + action + " " + nameEntity + ": " + "el " + entityName + " no existe");
    }

    public static ResponseEntity buildMessageResponseIsEmpty(String action, String entityName) {
        return ResponseEntity.status(204).body("No a enviado ningun " + entityName + " para " + action + ", por que esta vacio el cuerpo o es invalido");
    }
}
