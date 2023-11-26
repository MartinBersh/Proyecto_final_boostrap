package co.edu.cue.hotelvillegas.controllers;
import co.edu.cue.hotelvillegas.domain.entities.Room;
import co.edu.cue.hotelvillegas.mapping.dto.RoomDto;
import co.edu.cue.hotelvillegas.security.AuthorityImp;
import co.edu.cue.hotelvillegas.services.RoomServices;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/room")
public class RoomController {

    private final RoomServices service;
    public RoomController(RoomServices service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String listAllUser(Model model){
        List<RoomDto> roomList;
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isClient = userDetails.getAuthorities().stream().anyMatch(aut -> aut.getAuthority().equals("ROLE_CLIENT"));

        roomList =  isClient ? service.getAvailableRooms() : service.list();
        model.addAttribute("roomList", roomList);
        return "room/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("room", new Room());
        return "room/form";
    }

    @PostMapping("/save")
    public String save(RoomDto roomDto){
        service.save(roomDto);
        return "redirect:/room/list";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
         Optional<RoomDto> room = service.getRoomById(id);
         if(room.isPresent()) {
             System.out.println("Editing room");
             model.addAttribute("room", room.get());
             return "room/edit";
         }
        System.out.println("Non Editing room");
          return "redirect:/room/list";
    }
    @PostMapping("/update")
    public String update(RoomDto roomDto){
        service.update(roomDto);
        return "redirect:/room/list";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        System.out.println("Deleting room");
        service.delete(id);
        return "redirect:/room/list";
    }
}
