package com.example.estacionamento.controller;


import com.example.estacionamento.model.ParkingDTO;
import com.example.estacionamento.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/parking")
public class ParkingController {

    @Autowired
    ParkingService parkingService;

    @PostMapping("/{plate}")
    public ResponseEntity<String> registerCheckIn(@RequestParam String plate) throws Exception {
        return new ResponseEntity<>(parkingService.registerCheckIn(plate), HttpStatus.OK);
    }

    @PutMapping("/{plate}/out}")
    public ResponseEntity<Void> registerCheckOut(@RequestParam String plate) throws Exception {
        parkingService.registerCheckOut(plate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{plate}/pay")
    public ResponseEntity<String> registerPayment(@RequestParam String plate) throws Exception {
        parkingService.registerPayment(plate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{plate}")
    public ResponseEntity<List<ParkingDTO>> findAllByPlate(@RequestParam String plate) throws Exception {
        return new ResponseEntity<>(parkingService.findAllByPlate(plate), HttpStatus.OK);
    }
}
