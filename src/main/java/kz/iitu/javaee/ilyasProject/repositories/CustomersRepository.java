package kz.iitu.javaee.ilyasProject.repositories;

import kz.iitu.javaee.ilyasProject.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {

    Customers findByDeletedAtNullAndEmail(String email);
    Customers findByEmail(String email);
}
