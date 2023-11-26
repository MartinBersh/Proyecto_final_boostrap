package co.edu.cue.hotelvillegas.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_reservation")
    private Long idReservation;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "number_of_people")
    private int numberOfPeople;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private User client;
    @OneToOne
    private Room room;

}
