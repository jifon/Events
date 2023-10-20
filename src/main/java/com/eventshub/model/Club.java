package com.eventshub.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "club")
@AllArgsConstructor
@NoArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clubName;
    private String description;
    private String image;

    @ManyToMany
    @JoinTable(
            name = "user_club_subscriptions",
            joinColumns = @JoinColumn(name = "club_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> subscribers;


    @ManyToMany
    @JoinTable(
            name = "user_club_heading",
            joinColumns = @JoinColumn(name = "club_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> headers;

    @OneToMany(mappedBy = "clubOrganizer", cascade = CascadeType.ALL)
    private Set<Event> clubsEvents;
}
