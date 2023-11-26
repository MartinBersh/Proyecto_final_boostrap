package co.edu.cue.hotelvillegas.services;

import co.edu.cue.hotelvillegas.domain.entities.Reservation;
import co.edu.cue.hotelvillegas.domain.enums.Status;
import co.edu.cue.hotelvillegas.mapping.dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    List<ReservationDto> list();
    ReservationDto getReservationById(Long id);
    ReservationDto save(ReservationDto reservation);
    ReservationDto update(ReservationDto reservation);
    List<ReservationDto> getReservationsByClient(String username);
    void delete(Long id);
}
