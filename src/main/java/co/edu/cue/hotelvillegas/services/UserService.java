package co.edu.cue.hotelvillegas.services;

import co.edu.cue.hotelvillegas.domain.entities.User;
import co.edu.cue.hotelvillegas.mapping.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> list();
    UserDto getUserById(int id);
    UserDto save(UserDto user);
    UserDto update(UserDto user);
    Optional<UserDto> getUserByUserName(String userName);

}
