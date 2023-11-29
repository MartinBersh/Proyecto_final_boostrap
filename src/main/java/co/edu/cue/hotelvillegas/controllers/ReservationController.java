package co.edu.cue.hotelvillegas.controllers;

import co.edu.cue.hotelvillegas.domain.entities.Reservation;
import co.edu.cue.hotelvillegas.mapping.dto.*;
import co.edu.cue.hotelvillegas.mapping.mappers.ReservationMapper;
import co.edu.cue.hotelvillegas.mapping.mappers.RoomMapper;
import co.edu.cue.hotelvillegas.mapping.mappers.UserMapper;
import co.edu.cue.hotelvillegas.services.ReservationService;
import co.edu.cue.hotelvillegas.services.RoomServices;
import co.edu.cue.hotelvillegas.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;
    private final RoomServices roomServices;


    @PostMapping("/book")
    public String createReservation(CreateReservationDto reservation, RedirectAttributes redirectAttributes){
        Optional<RoomDto> roomDto = roomServices.getRoomById(reservation.idRoom());

        if(reservation.startDate().isBefore(LocalDateTime.now())) {
            redirectAttributes.addFlashAttribute("datesError", "La fecha de inicio no puede ser menor a la fecha actual");
            return "redirect:/reservation/create/" + reservation.idRoom();
        }

        Reservation reservationEntity = ReservationMapper.mapFrom(reservation);
        reservationEntity.setRoom(RoomMapper.mapFrom(roomDto.get()));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDto client = userService.getUserByUserName(userDetails.getUsername()).get();
        reservationEntity.setClient(UserMapper.mapFrom(client));

        reservationService.save(ReservationMapper.mapFrom(reservationEntity));
        return "redirect:/home";
    }

    @GetMapping("/create/{roomId}")
    public String createReservation(@PathVariable Long roomId, Model model){
        Optional<RoomDto> room = roomServices.getRoomById(roomId);
        if (room.isPresent()) {
            model.addAttribute("selectedRoom", room.get());
            CreateReservationDto createReservationDto = new CreateReservationDto(null,null,0,null,room.get().idRoom());
            model.addAttribute("reservation", createReservationDto);
            return "reservation/form";

        }
        return "reservation/form";
    }

    @GetMapping("/list")
    public String listAllReservation(Model model) {
        List<ReservationDto> reservationList;
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isClient = userDetails.getAuthorities().stream().anyMatch(aut -> aut.getAuthority().equals("ROLE_CLIENT"));
        reservationList = isClient ? reservationService.getReservationsByClient(userDetails.getUsername()) : reservationService.list();
        model.addAttribute("reservationList", reservationList);

        return "reservation/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        System.out.println("Deleting reservation");
        reservationService.delete(id);
        return "redirect:/reservation/list";
    }

}
