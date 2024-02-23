package com.platzi.pizzeria.utils;

public enum UserStatus {

    ACTIVE("Activo"),

    INACTIVE("Inactivo"),

    LOCKED("Bloqueado");

    private String value;

    UserStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean hasActive() {
        return this == ACTIVE;
    }

    public static UserStatus fromValue(String value) {
        return switch (value) {
            case "Activo" -> ACTIVE;
            case "Inactivo" -> INACTIVE;
            case "Bloqueado" -> LOCKED;
            default -> throw new IllegalArgumentException("UserStatus [" + value + "] not supported.");
        };
    }

}
