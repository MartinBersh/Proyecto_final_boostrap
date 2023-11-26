package co.edu.cue.hotelvillegas.mapping.dto;

import co.edu.cue.hotelvillegas.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public record UserDto (Long idUser,
                       String name,
                       String email,
                       String userName,
                       String password,
                       Role role){

}
