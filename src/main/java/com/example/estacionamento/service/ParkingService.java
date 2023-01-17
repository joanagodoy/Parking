package com.example.estacionamento.service;

import com.example.estacionamento.model.Parking;
import com.example.estacionamento.model.ParkingDTO;
import com.example.estacionamento.repository.ParkingRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    @Autowired
    ParkingRepository parkingRepository;

    public boolean isPlateCorrect(String plate){
        Pattern p = Pattern.compile("[A-Z]{3}-[0-9]{4}");
        Matcher m = p.matcher(plate);
        if (m.find())
            return true;
        return false;
    }

    public String registerCheckIn(String plate) throws Exception {
        if(!isPlateCorrect(plate)){
            throw new Exception("Formato da Placa Incoreto!");
        }

        Parking parking = Parking.builder()
                .plate(plate)
                .checkIn(LocalDateTime.now())
                .build();

        parkingRepository.save(parking);
        return StringUtils.leftPad(parking.getId().toString(), 6, "0");
    }

    public Parking findLastByPlate(String plate){
        return parkingRepository.findByPlateOrderByCheckInDesc(plate)
                .stream()
                .findFirst()
                .orElse(null);
    }

    public void registerCheckOut(String plate) throws Exception {
        Parking parking = this.findLastByPlate(plate);

        if(parking == null){
            throw new Exception("Registro de Placa não encontrado!");
        }

        if(!parking.isPaid){
            throw new Exception("Necessário pagamento para liberação!");
        }

        parking.setCheckOut(LocalDateTime.now());
        parkingRepository.save(parking);
    }

    public void registerPayment(String plate) throws Exception {
        Parking parking = this.findLastByPlate(plate);

        if(parking == null){
            throw new Exception("Registro de Placa não encontrado!");
        }

        parking.setPaid(true);
        parkingRepository.save(parking);
    }

    public List<ParkingDTO> findAllByPlate(String plate) throws Exception {
        List<Parking> parking = parkingRepository.findByPlateOrderByCheckInDesc(plate);
        return parking.stream().map(p ->
                    ParkingDTO
                        .builder()
                        .id(p.id)
                        .time(p.getCheckOut() != null ?
                                Duration.between(p.getCheckIn(), p.getCheckOut()).toString()
                                : Duration.between(p.getCheckIn(), LocalDateTime.now()).toString())
                        .paid(p.isPaid)
                        .exit(p.getCheckOut() != null)
                        .build()
                ).collect(Collectors.toList());
    }
}
