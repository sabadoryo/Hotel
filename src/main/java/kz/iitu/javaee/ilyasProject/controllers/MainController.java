package kz.iitu.javaee.ilyasProject.controllers;

import kz.iitu.javaee.ilyasProject.entities.Roles;
import kz.iitu.javaee.ilyasProject.entities.Rooms;
import kz.iitu.javaee.ilyasProject.entities.Users;
import kz.iitu.javaee.ilyasProject.repositories.RolesRepository;
import kz.iitu.javaee.ilyasProject.repositories.RoomsRepository;
import kz.iitu.javaee.ilyasProject.repositories.UserRepository;
import kz.iitu.javaee.ilyasProject.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Date;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private RoomService roomService;



    @GetMapping(path = "/")
    public String index(Model model) {

        model.addAttribute("classActiveSettingsIndexPage","active");
        return "annonymous/index";
    }

    @GetMapping(path = "/admin/index")
    public String indexAdmin(Model model) {
        return "index";
    }


    @GetMapping(path = "/login")
    public String login(Model model) {
        return "annonymous/login";
    }

    @GetMapping(path = "/rooms")
    public String rooms(Model model) {
        List<Rooms> all_rooms = roomsRepository.findAll();
        model.addAttribute("is_available_rooms", "Rooms");
        model.addAttribute("empty_rooms",all_rooms);
        model.addAttribute("classActiveSettingsRoomsPage","active");
        return "annonymous/rooms";
    }

    @GetMapping(path = "/profile")
    public String profile(Model model) {
        return "profile";
    }

    @GetMapping(path = "/registration")
    public String registrationPage(ModelMap model) {
        return "annonymous/registration";
    }

    @PostMapping(value = "/register")
    public String register(
            @RequestParam(name = "user_name") String name,
            @RequestParam(name = "user_email") String email,
            @RequestParam(name = "user_password") String password) {

        Set<Roles> roles = new HashSet<>();
        Roles r = rolesRepository.findById(1L).orElse(null);
        roles.add(r);
        Users user = new Users(email, password, name, roles);

        userRepository.save(user);
        return "annonymous/login";
    }

    @PostMapping(value = "/available_Rooms_By_Criteria_Main")
    public String searchRoomsMain(Model model,
                                  @RequestParam(name = "date_in") String date_in,
                                  @RequestParam(name = "date_out") String date_out,
                                  @RequestParam(name = "guests") int room_capacity,
                                  @RequestParam(name = "room") int room_size
            ) throws ParseException {

          List<Rooms> empty_rooms = roomService.getAvailableRooms(date_in,date_out,room_size,room_capacity);

          //Message if there is available rooms
          String is_available_rooms = "There is no available rooms";

          if(empty_rooms.size() > 0)
              is_available_rooms = "Available rooms:";

          model.addAttribute("classActiveSettingsRoomsPage","active");
          model.addAttribute("empty_rooms",empty_rooms);
          model.addAttribute("is_available_rooms",is_available_rooms);

          return "annonymous/rooms";

    }





}
