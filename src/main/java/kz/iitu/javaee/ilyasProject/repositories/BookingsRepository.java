package kz.iitu.javaee.ilyasProject.repositories;

import kz.iitu.javaee.ilyasProject.entities.Bookings;
import kz.iitu.javaee.ilyasProject.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Long> {


}
