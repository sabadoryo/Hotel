package kz.iitu.javaee.ilyasProject.services.impl;

import kz.iitu.javaee.ilyasProject.entities.*;
import kz.iitu.javaee.ilyasProject.repositories.*;
import kz.iitu.javaee.ilyasProject.services.EmailService;
import kz.iitu.javaee.ilyasProject.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomsRepository roomsRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    BookingsRepository bookingsRepository;

    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    ResourceRepository rr;

    @Autowired
    EventRepository er;

    @Autowired
    EmailService emailService;

    @Override
    public List<Rooms> getAvailableRooms(String date_In, String date_Out, int room_size, int room_capacity) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH);

        Date date_in = format.parse(date_In);
        Date date_out = format.parse(date_Out);


        List<Rooms> all_rooms = roomsRepository.findAll();
        List<Rooms> empty_rooms = new ArrayList<>();

        int count = 0;

        for (Rooms r : all_rooms) {
            if (r.getCapacity() == room_capacity && r.getSize() == room_size) {
                if (r.getBookings().size() == 0) {
                    r.setIsReserved(false);
                    empty_rooms.add(r);
                } else {

                    for (Bookings b : r.getBookings()) {
                        if (b.getStartDate().after(date_out) || b.getEndDate().before(date_in))
                            if (!(b.getEndDate().after(date_in) && b.getEndDate().before(date_out)))
                                if (!(b.getStartDate().after(date_in) && b.getStartDate().before(date_out))) {
                                    count++;
                                    if (r.getBookings().size() == count) {
                                        r.setIsReserved(false);
                                        empty_rooms.add(r);
                                    } else
                                        r.setIsReserved(true);
                                }
                    }
                }
            }
        }
        return empty_rooms;
    }

    @Override
    public List<Rooms> getAvailableRoomsFromCategory(Long id,String date_In, String date_Out, int room_size, int room_capacity) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH);

        Date date_in = format.parse(date_In);
        Date date_out = format.parse(date_Out);

        Category category = categoriesRepository.findById(id).orElse(null);

        assert category != null;
        Set<Rooms> all_rooms = category.getRooms();
        List<Rooms> empty_rooms = new ArrayList<>();

        int count = 0;

        for (Rooms r : all_rooms) {
            if (r.getCapacity() == room_capacity && r.getSize() == room_size) {
                if (r.getBookings().size() == 0) {
                    r.setIsReserved(false);
                    empty_rooms.add(r);
                } else {

                    for (Bookings b : r.getBookings()) {
                        if (b.getStartDate().after(date_out) || b.getEndDate().before(date_in))
                            if (!(b.getEndDate().after(date_in) && b.getEndDate().before(date_out)))
                                if (!(b.getStartDate().after(date_in) && b.getStartDate().before(date_out))) {
                                    count++;
                                    if (r.getBookings().size() == count) {
                                        r.setIsReserved(false);
                                        empty_rooms.add(r);
                                    } else
                                        r.setIsReserved(true);
                                }
                    }
                }
            }
        }
        return empty_rooms;
    }

    @Override
    public void addBookingToRoom(Long room_id, String date_in, String date_out,String email, String full_name, Long iin) throws ParseException {

        DateFormat format = new SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH);
        Date date_in1 = format.parse(date_in);
        Date date_out1 = format.parse(date_out);


        Bookings booking = new Bookings("This booking for room with id: " + room_id,date_in1,date_out1,"customer email:" + email + "fullname:"+ full_name);
        booking.setCreatedAt(new Date());
        bookingsRepository.save(booking);

        Customers customer = new Customers(email,full_name,iin);
        customer.setCreatedAt(new Date());
        customersRepository.save(customer);

        Rooms room = roomsRepository.findById(room_id).orElse(null);
        room.getBookings().add(booking);
        room.setUpdatedAt(new Date());
        roomsRepository.save(room);

        Event e = new Event();
        LocalDateTime ldt = LocalDateTime.ofInstant(date_in1.toInstant(), ZoneId.systemDefault());
        LocalDateTime ldt1 = LocalDateTime.ofInstant(date_out1.toInstant(), ZoneId.systemDefault());
        Resource r = rr.findById(1L).orElse(null);

        e.setStart(ldt);
        e.setEnd(ldt1);
        e.setText("Booking by:" + booking.getDescription());
        e.setResource(r);
        e.setRooms(room);
        er.save(e);



        emailService.sendSimpleMessage(email,"Your booking was created.",
                "Thank you for choosing our hotel\n " +
                        "Your booking details:" +
                        "\nStart date: "+date_in+"" +
                        "\nEnd date: " + date_out+"" +
                        "\nRoom number is: "+room.getNumber() +
                        "\nRoom description:" +room.getDescription() +"/"+room.getSize()+" sized" + "," + room.getBedInfo() +",\nServices:" +room.getServices());
    }
}

