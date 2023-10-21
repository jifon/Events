package com.eventshub.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
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
    @JsonIgnore
    private User organizer;

    @ManyToMany
    @JoinTable(
            name = "user_event_participants",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> participants;

    public void addParticipant(User user) {
        if (participants == null) {
            participants = new HashSet<>();
        }
        participants.add(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Club getClubOrganizer() {
        return clubOrganizer;
    }

    public void setClubOrganizer(Club clubOrganizer) {
        this.clubOrganizer = clubOrganizer;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }
}