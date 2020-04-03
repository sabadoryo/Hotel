package kz.iitu.javaee.ilyasProject.repositories;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import kz.iitu.javaee.ilyasProject.entities.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public interface EventRepository extends CrudRepository<Event, Long> {
    @Query("from Event e where not(e.end < :from and e.start > :to)")
    public List<Event> findBetween(@Param("from") LocalDateTime start, @Param("to") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime end);
    @Query("from Event e where(e.rooms.id =:idd)")
    public List<Event> findByStartAndEndAnAndRooms(@Param("idd")Long id);
}