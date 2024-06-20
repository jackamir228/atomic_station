package com.example.atomic_station.exceptions;

public class NuclearFuelIsEmptyException extends RuntimeException {
    public NuclearFuelIsEmptyException(String message) {
        super(message);
    }
}
