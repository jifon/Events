package com.eventshub.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "club")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "subscribers", "headers", "clubsEvents" })
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

    @JsonIgnore
    @OneToMany(mappedBy = "clubOrganizer", cascade = CascadeType.ALL)
    private Set<Event> clubsEvents;

}