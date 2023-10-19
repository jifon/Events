package com.eventshub.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "event")
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventName;
    private String place;
    private Date date;
    private String description;
    private boolean verified;
    private String image;

    @OneToMany(cascade = CascadeType.ALL)
    private List<User> participantsAndOrganizers;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club clubOrganizer;
}
