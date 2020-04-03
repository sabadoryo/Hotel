package kz.iitu.javaee.ilyasProject.repositories;


import kz.iitu.javaee.ilyasProject.entities.Resource;
import org.springframework.data.repository.CrudRepository;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
}