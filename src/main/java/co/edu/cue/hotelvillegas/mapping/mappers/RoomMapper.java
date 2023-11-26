package co.edu.cue.hotelvillegas.mapping.mappers;



import co.edu.cue.hotelvillegas.domain.entities.Room;
import co.edu.cue.hotelvillegas.mapping.dto.RoomDto;

import java.util.List;

public class RoomMapper {

    public static Room mapFrom(RoomDto source){
        return new Room(source.idRoom(),
                source.roomNumber(),
                source.price(),
                source.status());
    }

    public static RoomDto mapFrom(Room source){
        return new RoomDto(source.getIdRoom(),
                source.getRoomNumber(),
                source.getPrice(),
                source.getStatus());
    }

    public static List<RoomDto> mapFrom(List<Room> source){
        return source.parallelStream().map(e->mapFrom(e)).toList();
    }

}
