package kz.iitu.javaee.ilyasProject.services.impl;

import kz.iitu.javaee.ilyasProject.entities.Bookings;
import kz.iitu.javaee.ilyasProject.entities.Rooms;
import kz.iitu.javaee.ilyasProject.repositories.RoomsRepository;
import kz.iitu.javaee.ilyasProject.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomsRepository roomsRepository;

    @Override
    public List<Rooms> getAvailableRooms(Date date_in, Date date_out, int room_size, int room_capacity) {
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
}

