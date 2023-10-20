package com.eventshub.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
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

////    @JsonIgnore
//<<<<<<< HEAD
//    @OneToMany(mappedBy = "clubOrganizer", cascade = CascadeType.ALL)
//    private Set<Event> clubsEvents;
//=======
////    @OneToMany(mappedBy = "clubOrganizer", cascade = CascadeType.ALL)
////    private Set<Event> clubsEvents  = new HashSet<>();;
//>>>>>>> origin/master

    public Club(String clubName, String description, String image) {
        this.clubName = clubName;
        this.description = description;
        this.image = image;
        this.subscribers = new HashSet<>();
    }
}