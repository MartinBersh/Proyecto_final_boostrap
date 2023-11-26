package co.edu.cue.hotelvillegas.mapping.dto;

import co.edu.cue.hotelvillegas.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
@Builder
public record RoomDto(Long idRoom,
                      String roomNumber,
                      int price,
                      Status status) {
}
