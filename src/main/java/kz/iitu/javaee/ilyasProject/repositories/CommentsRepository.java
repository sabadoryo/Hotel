package kz.iitu.javaee.ilyasProject.repositories;

import kz.iitu.javaee.ilyasProject.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {


}
