package co.edu.cue.hotelvillegas.domain.entities;

import co.edu.cue.hotelvillegas.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    private String name;
    private String email;
    @Column(name = "user_name")
    private String userName;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

}
