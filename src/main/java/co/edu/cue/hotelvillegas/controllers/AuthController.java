package co.edu.cue.hotelvillegas.controllers;

import co.edu.cue.hotelvillegas.exceptions.UserException;
import co.edu.cue.hotelvillegas.mapping.dto.UserDto;
import co.edu.cue.hotelvillegas.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }
    @GetMapping("/sign-up")
    public String signUpForm(){
        return "auth/sign-up";
    }
    @PostMapping("/sign-up")
    public String signUp(UserDto userDto, Model model, RedirectAttributes redirectAttributes){
        try {
            userService.save(userDto);

        }catch (UserException e){
            redirectAttributes.addFlashAttribute("usernameError", e.getMessage());
            return "redirect:/sign-up";
        }
        return "redirect:/login";
    }
}
