package kz.iitu.javaee.ilyasProject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_customers")
public class Customers extends BaseEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "IIN")
    private Long iin;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<Bookings> bookings;

}
