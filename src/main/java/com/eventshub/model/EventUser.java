package com.eventshub.model;


import com.eventshub.model.enums.EventUserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@Table(name = "event_user")
@AllArgsConstructor
@NoArgsConstructor
public class EventUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //organizer or participant
    @Enumerated(EnumType.STRING)
    private EventUserStatus eventUserStatus;

}
