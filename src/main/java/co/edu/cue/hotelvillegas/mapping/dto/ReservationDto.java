package co.edu.cue.hotelvillegas.mapping.dto;

import co.edu.cue.hotelvillegas.domain.entities.Room;
import co.edu.cue.hotelvillegas.domain.entities.User;
import lombok.Builder;


import java.time.LocalDateTime;

@Builder
public record ReservationDto (Long idReservation,
                              LocalDateTime startDate,
                              LocalDateTime endDate,
                              int numberOfPeople,
                              User client,
                              Room room){

}
