package co.edu.cue.hotelvillegas.security;

import co.edu.cue.hotelvillegas.domain.entities.User;
import co.edu.cue.hotelvillegas.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserDetailsServiceImp implements UserDetailsService{
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUserName(username);
    if(user.isPresent()){
      return new UserDetailsImp(user.get());
    }
    throw new UsernameNotFoundException("User not found");
  }
}
