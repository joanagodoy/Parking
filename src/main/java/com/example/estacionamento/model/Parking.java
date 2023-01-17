package com.example.estacionamento.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@Table(name = "parking")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_parking")
    public Long id;
    public String plate; // fazer um value object
    public String booking;
    public LocalDateTime checkIn;
    public LocalDateTime checkOut;
    public boolean isPaid; //fazer outra classe
}
