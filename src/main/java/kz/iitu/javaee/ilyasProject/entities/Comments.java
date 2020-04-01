package kz.iitu.javaee.ilyasProject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_comments")
public class Comments extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @Column(name = "name_person")
    private String name_person;

    @Column(name = "email_person")
    private String email_person;

    @Column(name = "review")
    private String review;

    @Column(name = "date")
    private Date date;
}