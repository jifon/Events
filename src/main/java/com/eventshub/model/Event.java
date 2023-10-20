package com.eventshub.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "event")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "organizer", "participants", "clubOrganizer" })
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

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club clubOrganizer;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;

    @ManyToMany
    @JoinTable(
            name = "user_event_participants",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> participants;


}