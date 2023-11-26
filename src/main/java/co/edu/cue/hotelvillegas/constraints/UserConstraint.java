package co.edu.cue.hotelvillegas.constraints;

import co.edu.cue.hotelvillegas.exceptions.UserException;
import co.edu.cue.hotelvillegas.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConstraint {
  private final UserRepository repository;

  public void isUserNameAvailable(String userName) throws UserException {
    repository.findByUserName(userName).ifPresent(user -> {
          throw new UserException("El nombre de usuario ya existe");
        });
  }
}
