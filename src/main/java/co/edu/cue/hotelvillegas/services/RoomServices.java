package co.edu.cue.hotelvillegas.services;

import co.edu.cue.hotelvillegas.mapping.dto.RoomDto;

import java.util.List;
import java.util.Optional;

public interface RoomServices {
    List<RoomDto> list();
    Optional<RoomDto> getRoomById(Long id);
    RoomDto save(RoomDto room);
    RoomDto update(RoomDto room);
    void delete(Long id);
    List<RoomDto> getAvailableRooms();
}