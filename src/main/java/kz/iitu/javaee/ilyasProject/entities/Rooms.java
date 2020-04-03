package kz.iitu.javaee.ilyasProject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Column(name = "bedInfo")
    private String bedInfo;

    @Column(name = "services")
    private String services;

    @Column(name = "cost")
    private Double cost;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Bookings> bookings;

    public boolean isEmptyCheck(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date d = new Date();

        boolean ch = true;
        if(this.getBookings() != null) {
            for (Bookings b : this.getBookings()) {
                System.out.println("BOOKINGS" + b);
                if (d.after(b.getStartDate()) && d.before(b.getEndDate())) {
                    ch = false;
                    break;
                }
            }
        }
        return ch;
    }
}