package kz.iitu.javaee.ilyasProject.services;

import kz.iitu.javaee.ilyasProject.entities.Rooms;

import java.util.Date;
import java.util.List;

public interface RoomService {
    List<Rooms> getAvailableRooms(Date date_in, Date date_out, int room_size, int room_capacity);
}
