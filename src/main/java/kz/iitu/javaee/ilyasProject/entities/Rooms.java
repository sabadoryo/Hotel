package kz.iitu.javaee.ilyasProject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_rooms")
public class Rooms extends BaseEntity {

    @Column(name = "number")
    private int number;

    @Column(name = "size")
    private int size;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "description")
    private String description;

    @Column(name = "isEmpty")
    private Boolean isEmpty;

    @Column(name = "isReserved")
    private Boolean isReserved;

    @Column(name = "picture")
    private String picturePath;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Bookings> bookings;

}