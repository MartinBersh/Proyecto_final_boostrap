package co.edu.cue.hotelvillegas.controllers;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

        @RequestMapping("")
        public String index(Model model){
            SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(aut-> System.out.println(aut.getAuthority()));
            List<String> images = Arrays.asList(
                    "/images/image1.jpg",
                    "/images/image2.jpg",
                    "/images/image3.jpg"
            );
            model.addAttribute("images", images);
            return "home/index";
        }
}
