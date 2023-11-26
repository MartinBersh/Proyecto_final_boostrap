package co.edu.cue.hotelvillegas.mapping.mappers;


import co.edu.cue.hotelvillegas.domain.entities.Reservation;
import co.edu.cue.hotelvillegas.mapping.dto.CreateReservationDto;
import co.edu.cue.hotelvillegas.mapping.dto.ReservationDto;

import java.util.List;

public class ReservationMapper {

    public static ReservationDto mapFrom(Reservation source){
        return new ReservationDto(source.getIdReservation(),
                source.getStartDate(),
                source.getEndDate(),
                source.getNumberOfPeople(),
                source.getClient(),
                source.getRoom());
    }

    public static Reservation mapFrom(ReservationDto source){
        return new Reservation(source.idReservation(),
                source.startDate(),
                source.endDate(),
                source.numberOfPeople(),
                source.client(),
                source.room());
    }
    public static Reservation mapFrom(CreateReservationDto source){
        return new Reservation(
                null,
                source.startDate(),
                source.endDate(),
                source.numberOfPeople(),
                null,
                null);
    }
    public static CreateReservationDto mapFromCreateReservation(Reservation source){
        return new CreateReservationDto(
                source.getStartDate(),
                source.getEndDate(),
                source.getNumberOfPeople(),
                source.getRoom() == null ? null : source.getRoom().getIdRoom(),
                source.getClient() == null ? null : source.getClient().getIdUser());
    }

    public static List<ReservationDto> mapFrom(List<Reservation > source){
        return source.parallelStream().map(e->mapFrom(e)).toList();
    }
}
