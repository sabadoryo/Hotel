package kz.iitu.javaee.ilyasProject.controllers;

import kz.iitu.javaee.ilyasProject.entities.*;
import kz.iitu.javaee.ilyasProject.repositories.*;
import kz.iitu.javaee.ilyasProject.services.EmailService;
import kz.iitu.javaee.ilyasProject.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.mail.MessagingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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

    @Autowired
    private CategoriesRepository categoryRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    public SimpleMailMessage template;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private BookingsRepository bookingsRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private EventRepository eventRepository;


    @GetMapping(path = "/")
    public String index(Model model) throws MessagingException {
        List<Rooms> rooms = roomsRepository.findAll();
        model.addAttribute("classActiveSettingsIndexPage", "active");
        return "annonymous/index";
    }

    @GetMapping(path = "/admin/index")
    public String indexAdmin(Model model) {
        return "index";
    }


    @GetMapping(path = "/admin")
    public String login(Model model) {
        return "admin/login";
    }


    @GetMapping(path = "/rooms")
    public String rooms(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("classActiveSettingsRoomsPage", "active");
        return "annonymous/rooms_category";
    }

    @GetMapping(path ="/about-us")
    public String about_us(Model model) {
        return "annonymous/about-us";
    }

    @GetMapping(path ="/blog")
    public String blog(Model model) {
        return "annonymous/blog";
    }

    @GetMapping(path ="/contact")
    public String contact(Model model) {
        return "annonymous/contact";
    }

    @GetMapping(path = "/admin/profile")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public String profile(Model model) {

        List<Rooms> rooms = roomsRepository.findAll();
        List<Customers> customers = customersRepository.findAll();

        Category gold = categoryRepository.findById(1L).orElse(null);
        Category silver = categoryRepository.findById(2L).orElse(null);
        Category bronze = categoryRepository.findById(3L).orElse(null);


        int bookings_count = 0;
        int empty_rooms_count = 0;
        int active_bookings_count = 0;


        for(Rooms r: rooms){
            if(rooms.isEmpty()){
                empty_rooms_count++;
            }
            for(Bookings b: r.getBookings()){
                if(b.getEndDate().before(new Date())){
                    active_bookings_count++;
                }
                bookings_count++;
            }

        }
        int not_empty_rooms_count = rooms.size() - empty_rooms_count;

        model.addAttribute("rooms_count", rooms.size());
        model.addAttribute("bookings_count", bookings_count);
        model.addAttribute("empty_rooms_count",empty_rooms_count);
        model.addAttribute("not_empty_rooms_count", not_empty_rooms_count);
        model.addAttribute("gold_rooms_count",gold.getRooms().size());
        model.addAttribute("silver_rooms_count", silver.getRooms().size());
        model.addAttribute("bronze_rooms_count", bronze.getRooms().size());
        model.addAttribute("active_bookings_count", bronze.getRooms().size());
        model.addAttribute("customers_count", customers.size());
        model.addAttribute("active_bookings_count", active_bookings_count);

        List<Bookings> bookings = bookingsRepository.findAll();

        model.addAttribute("bookings", bookings);
        return "admin/profile";
    }

    @GetMapping(path = "/admin/profile/list_of_rooms")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String list_of_rooms(Model model) {

        List<Rooms> rooms = roomsRepository.findAll();
        for (Rooms r : rooms) {
            r.setIsEmpty(r.isEmptyCheck());
            roomsRepository.save(r);
        }
        List<Rooms> rooms1 = roomsRepository.findAll();

        model.addAttribute("list_of_rooms", rooms1);

        return "admin/list_of_rooms";
    }
    @GetMapping(path = "/admin/profile/list_of_rooms/{id}/bookings_calendar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String bookingsCalendar(Model model,@PathVariable(name = "id") Long id)
    {
        Rooms room = roomsRepository.findById(id).orElse(null);

        model.addAttribute("room", room);
        model.addAttribute("rooms_id",id);
        return "admin/bookings_calendar";
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

    @GetMapping(value = "/add_room_page")
    public String addRoomPage() {
        return "admin/add_room_page";
    }

    @PostMapping(value = "/add_room")
    public String addRoom(@RequestParam(name = "number") int number,
                          @RequestParam(name = "size") int size,
                          @RequestParam(name = "capacity") int capacity,
                          @RequestParam(name = "type_of_the_room") String type_of_the_room,
                          @RequestParam(name = "image") String image,
                          @RequestParam(name = "bed_info") String bed_info,
                          @RequestParam(name = "services") String services,
                          @RequestParam(name = "cost") Double cost
    ) {
        Rooms room = new Rooms(number, size, capacity, type_of_the_room,
                false, false, "img/room/" + image, bed_info, services, cost, null);
        roomsRepository.save(room);

        return "admin/profile";
    }

    @GetMapping(path = "/deleteRoom/{id}")
    public String deleteRoom(@PathVariable(name = "id") Long id) {
        List<Rooms> items = roomsRepository.findAll();
        for (Rooms i : items) {
            if (i.getId().equals(id)) {
                roomsRepository.deleteById(i.getId());
            }
        }
        return "redirect:/list_of_rooms";
    }

    @GetMapping(path = "/deleteBooking/{id}")
    public String deleteBooking(@PathVariable(name = "id") Long id){
        List<Bookings> bookings = bookingsRepository.findAll();
        List<Event> events = (List<Event>) eventRepository.findAll();
        String tmp = "";
        for(Bookings b: bookings) {
            if(b.getId().equals(id)) {
                tmp = b.getTitle();
                bookingsRepository.deleteById(b.getId());
            }
        }
        String last2 = "";
        last2 = tmp.substring(tmp.length() - 2);
        long room_id = Long.parseLong(last2);
        for(Event e: events){
            if(e.getRooms().getId().equals(room_id)) {
                eventRepository.deleteById(e.getId());
            }
        }
        return "redirect:/admin/profile";
    }

    @GetMapping(path = "/editRoom/{id}")
    public String editRoom(Model model, @PathVariable(name = "id") Long id) {
        Rooms room_edit = roomsRepository.findById(id).orElse(null);
        model.addAttribute("room_edit", room_edit);
        return "admin/editRoom";
    }

    @PostMapping(value = "/updateRoom")
    public String updateComment(
            @RequestParam(name = "room_id") Long id,
            @RequestParam(name = "type_of_the_room") String description,
            @RequestParam(name = "capacity") int capacity,
            @RequestParam(name = "size") int size,
            @RequestParam(name = "number") int number,
            @RequestParam(name = "bed_info") String bed_info,
            @RequestParam(name = "services") String services,
            @RequestParam(name = "cost") Double cost,
            @RequestParam(name = "image") String image) {

        Rooms rooms = roomsRepository.findById(id).orElse(null);
        rooms.setDescription(description);
        rooms.setCapacity(capacity);
        rooms.setServices(services);
        rooms.setSize(size);
        rooms.setNumber(number);
        rooms.setBedInfo(bed_info);
        rooms.setCost(cost);
        rooms.setPicturePath("img/room/" + image);

        roomsRepository.save(rooms);

        return "redirect:/list_of_rooms";
    }

    @PostMapping(value = "/available_Rooms_By_Criteria_Main")
    public String searchRoomsMain(Model model,
                                  @RequestParam(name = "date_in") String date_in,
                                  @RequestParam(name = "date_out") String date_out,
                                  @RequestParam(name = "guests") int room_capacity,
                                  @RequestParam(name = "room") int room_size
    ) throws ParseException {

        List<Rooms> empty_rooms = roomService.getAvailableRooms(date_in, date_out, room_size, room_capacity);

        //Message if there is available rooms
        String is_available_rooms = "There is no available requested rooms, try to search again:";
        String url_home = "click here";
        if (empty_rooms.size() > 0) {
            is_available_rooms = "Available rooms:";
            url_home = " ";
        }
        model.addAttribute("date_in", date_in);
        model.addAttribute("date_out", date_out);
        model.addAttribute("classActiveSettingsRoomsPage", "active");
        model.addAttribute("empty_rooms", empty_rooms);
        model.addAttribute("is_available_rooms", is_available_rooms);
        model.addAttribute("url_home", url_home);
        return "annonymous/rooms";

    }

    @GetMapping(path = "/details/category/{id}")
    public String detailsCategory(ModelMap model, @PathVariable(name = "id") Long id) {

        Category category = categoryRepository.findById(id).orElse(null);
        List<Comments> comments = commentsRepository.findAll();
        List<Comments> category_com = new ArrayList<>();

        for(Comments c: comments){
            if(c.getCategory().getId().equals(id)){
                category_com.add(c);
            }
        }

        model.addAttribute("category_com", category_com);
        model.addAttribute("category", category);
        return "annonymous/category_details";
    }

    @PostMapping(value = "/available_Rooms_By_Criteria_Category")
    public String searchRoomsCategory(Model model,
                                      @RequestParam(name = "date_in") String date_in,
                                      @RequestParam(name = "date_out") String date_out,
                                      @RequestParam(name = "guests") int room_capacity,
                                      @RequestParam(name = "room") int room_size,
                                      @RequestParam(name = "category_id") Long id
    ) throws ParseException {

        List<Rooms> empty_rooms = roomService.getAvailableRoomsFromCategory(id, date_in, date_out, room_size, room_capacity);

        //Message if there is available rooms
        String is_available_rooms = "There is no available requested rooms, try to search again:";

        String url_home = "click here";
        if (empty_rooms.size() > 0) {
            is_available_rooms = "Available rooms:";
            url_home = " ";
        }

        model.addAttribute("date_in", date_in);
        model.addAttribute("date_out", date_out);
        model.addAttribute("classActiveSettingsRoomsPage", "active");
        model.addAttribute("empty_rooms", empty_rooms);
        model.addAttribute("is_available_rooms", is_available_rooms);
        model.addAttribute("url_home", url_home);
        return "annonymous/rooms";

    }


    @GetMapping(path = "/details/room/{id}/{date_in}/{date_out}")
    public String detailsRoom(ModelMap model, @PathVariable(name = "id") Long id,
                              @PathVariable(name = "date_in") String date_in,
                              @PathVariable(name = "date_out") String date_out) {

        Rooms room = roomsRepository.findById(id).orElse(null);
        model.addAttribute("room", room);
        model.addAttribute("date_in", date_in);
        model.addAttribute("date_out", date_out);
        return "annonymous/room_details";
    }

    @PostMapping(path = "/addBooking")
    public String addBooking(@RequestParam(name = "room_id") Long id,
                             @RequestParam(name = "date_in") String date_in,
                             @RequestParam(name = "date_out") String date_out,
                             @RequestParam(name = "customer_email") String email,
                             @RequestParam(name = "customer_fullName") String full_name,
                             @RequestParam(name = "customer_iin") Long iin) throws ParseException {

        roomService.addBookingToRoom(id, date_in, date_out, email, full_name, iin);
        return "redirect:/";
    }

    @PostMapping(path = "/addComment")
    public String addComment(@RequestParam(name = "category_id") Long id,
                             @RequestParam(name = "name_person") String name,
                             @RequestParam(name = "email_person") String email,
                             @RequestParam(name = "review") String review){

        Category category = categoryRepository.findById(id).orElse(null);

        DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        Date date = new Date();

        Comments comments = new Comments(category, name, email, review, date);
        commentsRepository.save(comments);

        return "redirect:/details/category/" + id;
    }


}
