package co.edu.cue.hotelvillegas.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class AuthorityImp implements GrantedAuthority {
  private final String authority;
  @Override
  public String getAuthority() {
    return authority;
  }
}
