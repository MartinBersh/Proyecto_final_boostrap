package co.edu.cue.hotelvillegas.services.impl;

import co.edu.cue.hotelvillegas.domain.entities.Reservation;
import co.edu.cue.hotelvillegas.domain.entities.Room;
import co.edu.cue.hotelvillegas.domain.enums.Status;
import co.edu.cue.hotelvillegas.mapping.dto.ReservationDto;
import co.edu.cue.hotelvillegas.mapping.mappers.ReservationMapper;
import co.edu.cue.hotelvillegas.mapping.mappers.RoomMapper;
import co.edu.cue.hotelvillegas.repositories.ReservationRepository;
import co.edu.cue.hotelvillegas.repositories.RoomRepository;
import co.edu.cue.hotelvillegas.services.ReservationService;
import co.edu.cue.hotelvillegas.services.RoomServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RerservationServiceImpl implements ReservationService {
    private final ReservationRepository repository;
    @Autowired
    private RoomServices roomServices;

    public RerservationServiceImpl(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ReservationDto> list() {
        return ReservationMapper.mapFrom((List<Reservation>) repository.findAll());
    }

    @Override
    public ReservationDto getReservationById(Long id) {
        return ReservationMapper.mapFrom(
                repository.findById(id).orElseThrow());
    }

    @Override
    public ReservationDto save(ReservationDto reservation) {
        updateRoomStatus(reservation);
        return ReservationMapper.mapFrom(
                repository.save(ReservationMapper.mapFrom(reservation)));
    }

    @Override
    public ReservationDto update(ReservationDto reservation) {
        return ReservationMapper.mapFrom(
                repository.save(ReservationMapper.mapFrom(reservation)));
    }

    @Override
    public List<ReservationDto> getReservationsByClient(String username) {
        return list().stream()
                .filter(reservation -> reservation.client().getUserName().equals(username))
                .toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void updateRoomStatus(ReservationDto reservation){
        Room room = reservation.room();
        room.setStatus(Status.RESERVED);

        roomServices.update(RoomMapper.mapFrom(room));
    }
}
