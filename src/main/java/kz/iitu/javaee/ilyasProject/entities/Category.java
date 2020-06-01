package kz.iitu.javaee.ilyasProject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_category")
public class Category extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "picture")
    private String picturePath;

    @Column(name = "rating")
    private Integer rating;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Rooms> rooms;

}