package co.edu.cue.hotelvillegas.repositories;

import co.edu.cue.hotelvillegas.domain.entities.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation,Long> {
}
