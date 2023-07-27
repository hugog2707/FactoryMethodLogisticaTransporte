package com.demo.FactoryMethodLogisticaTransporte.utils;

public class ErrorUtils {
    public static String buildMessageError(String nameEntity, String action, Exception e) {
        return "Error al " + action + " " + nameEntity + ": " + e.getMessage();
    }
}
