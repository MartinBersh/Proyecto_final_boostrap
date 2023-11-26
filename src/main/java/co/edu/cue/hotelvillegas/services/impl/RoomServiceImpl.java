package co.edu.cue.hotelvillegas.services.impl;


import co.edu.cue.hotelvillegas.domain.entities.Room;
import co.edu.cue.hotelvillegas.domain.enums.Status;
import co.edu.cue.hotelvillegas.mapping.dto.RoomDto;
import co.edu.cue.hotelvillegas.mapping.mappers.RoomMapper;
import co.edu.cue.hotelvillegas.repositories.RoomRepository;
import co.edu.cue.hotelvillegas.services.RoomServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomServices {
    private final RoomRepository repository;

    public RoomServiceImpl(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RoomDto> list() {
        return RoomMapper.mapFrom((List<Room>)repository.findAll());
    }

    public Optional<RoomDto> getRoomById(Long id) {
        return repository.findById(id).map(RoomMapper::mapFrom);
    }

    @Override
    public RoomDto save(RoomDto room) {
        return RoomMapper.mapFrom(
                repository.save(
                        RoomMapper.mapFrom(room))
        );
    }

    @Override
    public RoomDto update(RoomDto room) {
        return RoomMapper.mapFrom(
                repository.save(RoomMapper.mapFrom(room)
                )
        );
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<RoomDto> getAvailableRooms() {
        return list().stream()
                .filter(room -> room.status().equals(Status.AVAILABLE))
                .toList();
    }

}
