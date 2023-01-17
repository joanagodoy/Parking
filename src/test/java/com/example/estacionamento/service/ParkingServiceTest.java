package com.example.estacionamento.service;

import com.example.estacionamento.model.ParkingDTO;
import com.example.estacionamento.repository.ParkingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class ParkingServiceTest {

    @InjectMocks
    ParkingService parkingService;

    @Mock
    ParkingRepository parkingRepository;

    @Test
    public void shoudlRegisterEntranceAndReturnBookingNumber() throws Exception {
        String plate = "AAA-9999"; //todo criar faker
        String bookingNumber = "000001";
        Mockito.when(parkingService.registerCheckIn(plate)).thenReturn(bookingNumber);
        Assertions.assertEquals("000001", bookingNumber);
    }

    @Test
    public void shouldReturnFalseWhenValidatePlate() throws Exception {
        Assertions.assertEquals(parkingService.isPlateCorrect("999-9999"), false);
        Assertions.assertEquals(parkingService.isPlateCorrect("999-AAAA"), false);
        Assertions.assertEquals(parkingService.isPlateCorrect("9999999"), false);
        Assertions.assertEquals(parkingService.isPlateCorrect("AAAAAAA"), false);
    }

    @Test
    public void shouldReturnTrueWhenValidatePlate() throws Exception {
        Assertions.assertEquals(true, parkingService.isPlateCorrect("FAA-1234"));
    }

    @Test
    public void shouldRegisterCheckOut() throws Exception {
        String plate = "AAA-9999"; //todo criar faker
        Mockito.doNothing().when(parkingService).registerCheckOut(plate);
    }

    @Test
    public void shouldThrowExceptionWhenRegisterCheckOut() throws Exception {
        String plate = "AAA-9999"; //todo criar faker
        Mockito.doThrow(new Exception()).when(parkingService).registerCheckOut(plate);
    }

    @Test
    public void shouldRegisterPayment() throws Exception {
        String plate = "AAA-9999"; //todo criar faker
        Mockito.doNothing().when(parkingService).registerPayment(plate);
    }

    @Test
    public void shoudlReturnParkingHistory() throws Exception {
        String plate = "AAA-9999"; //todo criar faker

        List<ParkingDTO> history = List.of(
                ParkingDTO.builder()
                        .id(1L)
                        .time("30 minutes")
                        .paid(true)
                        .exit(true)
                        .build(),
                ParkingDTO.builder()
                        .id(2L)
                        .time("60 minutes")
                        .paid(false)
                        .exit(false)
                        .build(),
                ParkingDTO.builder()
                        .id(3L)
                        .time("90 minutes")
                        .paid(true)
                        .exit(false)
                        .build()
                );

        Mockito.when(parkingService.findAllByPlate(plate)).thenReturn(history);
    }
}
