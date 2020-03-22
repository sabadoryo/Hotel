package kz.iitu.javaee.ilyasProject.repositories;

import kz.iitu.javaee.ilyasProject.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long> {


}
