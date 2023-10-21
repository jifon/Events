package com.eventshub.payload.response;

import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
public class ClubWithEventsCount {
    private Long id;
    private String clubName;
    private String description;
    private String image;
    private Integer eventsCount;
}
