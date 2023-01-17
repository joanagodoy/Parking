package com.example.estacionamento.model;

import lombok.Builder;

@Builder
public class ParkingDTO {

    public Long    id;
    public String  time;
    public String  booking;
    public boolean  exit;
    public boolean paid;
}
