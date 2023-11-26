package co.edu.cue.hotelvillegas.domain.entities;

import co.edu.cue.hotelvillegas.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room")
    private Long idRoom;
    private String roomNumber;
    private int price;
    @Enumerated(EnumType.STRING)
    private Status status;


}
