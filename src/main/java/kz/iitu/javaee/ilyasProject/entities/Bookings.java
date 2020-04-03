package kz.iitu.javaee.ilyasProject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_booking")
public class Bookings extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "description")
    private String description;


}