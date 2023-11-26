package co.edu.cue.hotelvillegas.mapping.dto;

import java.time.LocalDateTime;

public record CreateReservationDto(
  LocalDateTime startDate,
  LocalDateTime endDate,
  int numberOfPeople,
  Long idClient,
  Long idRoom
) {}
