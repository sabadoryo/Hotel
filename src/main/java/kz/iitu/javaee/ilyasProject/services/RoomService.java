package kz.iitu.javaee.ilyasProject.services;

import kz.iitu.javaee.ilyasProject.entities.Rooms;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface RoomService {
    List<Rooms> getAvailableRooms(String date_in, String date_out, int room_size, int room_capacity) throws ParseException;
    List<Rooms> getAvailableRoomsFromCategory(Long id,String date_in, String date_out, int room_size, int room_capacity)throws ParseException;
}
