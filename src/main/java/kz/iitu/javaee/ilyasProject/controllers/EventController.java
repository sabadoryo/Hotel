package kz.iitu.javaee.ilyasProject.controllers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import kz.iitu.javaee.ilyasProject.entities.Bookings;
import kz.iitu.javaee.ilyasProject.entities.Event;
import kz.iitu.javaee.ilyasProject.entities.Resource;
import kz.iitu.javaee.ilyasProject.entities.Rooms;
import kz.iitu.javaee.ilyasProject.repositories.EventRepository;
import kz.iitu.javaee.ilyasProject.repositories.ResourceRepository;
import kz.iitu.javaee.ilyasProject.repositories.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    EventRepository er;

    @Autowired
    ResourceRepository rr;

    @Autowired
    private RoomsRepository roomsRepository;

    @RequestMapping("/api")
    @ResponseBody
    String home(){
        return "Welcome!";
    }
    @RequestMapping("/api/resources")
    Iterable<Resource> resources() {
        return rr.findAll();
    }

    @GetMapping("/api/events/{id}")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Event> events(@PathVariable(name = "id") Long id) throws ParseException {
        System.out.println("ASDASDASDASDAS" + er.findByStartAndEndAnAndRooms(id));
        return er.findByStartAndEndAnAndRooms(id);
    }

    @PostMapping("/api/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event createEvent(@RequestBody EventCreateParams params) {

        Resource r = rr.findById(params.resource).orElse(null);

        Event e = new Event();
        e.setStart(params.start);
        e.setEnd(params.end);
        e.setText(params.text);
        e.setResource(r);

        er.save(e);

        return e;
    }


    @PostMapping("/api/events/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event moveEvent(@RequestBody EventMoveParams params) {

        Event e = er.findById(params.id).orElse(null);
        Resource r = rr.findById(params.resource).orElse(null);

        e.setStart(params.start);
        e.setEnd(params.end);
        e.setResource(r);

        er.save(e);

        return e;
    }

    @PostMapping("/api/events/setColor")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event setColor(@RequestBody SetColorParams params) {

        Event e = er.findById(params.id).orElse(null);
        e.setColor(params.color);
        er.save(e);

        return e;
    }

    public static class EventCreateParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
        public Long resource;
    }

    public static class EventMoveParams {
        public Long id;
        public LocalDateTime start;
        public LocalDateTime end;
        public Long resource;
    }

    public static class SetColorParams {
        public Long id;
        public String color;
    }

}
