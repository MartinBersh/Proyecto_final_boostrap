package co.edu.cue.hotelvillegas.mapping.mappers;

import co.edu.cue.hotelvillegas.domain.entities.Reservation;
import co.edu.cue.hotelvillegas.domain.entities.User;
import co.edu.cue.hotelvillegas.mapping.dto.ReservationDto;
import co.edu.cue.hotelvillegas.mapping.dto.UserDto;

import javax.swing.plaf.PanelUI;
import java.util.List;

public class UserMapper {

    public static User mapFrom(UserDto source) {
        return new User(source.idUser(),
                source.name(),
                source.email(),
                source.userName(),
                source.password(),
                source.role());
    }

    public static UserDto mapFrom(User source){
        return new UserDto(source.getIdUser(),
                source.getName(),
                source.getEmail(),
                source.getUserName(),
                source.getPassword(),
                source.getRole());
    }
    public static List<UserDto> mapFrom(List<User> source){
        return source.parallelStream().map(e->mapFrom(e)).toList();
    }

}
