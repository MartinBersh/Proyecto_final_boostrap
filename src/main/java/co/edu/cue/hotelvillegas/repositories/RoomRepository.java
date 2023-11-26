package co.edu.cue.hotelvillegas.repositories;

import co.edu.cue.hotelvillegas.domain.entities.Room;
import co.edu.cue.hotelvillegas.mapping.dto.RoomDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room,Long> {


}
