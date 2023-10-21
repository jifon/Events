package com.eventshub.payload.response;

import lombok.Setter;

import java.util.Date;

@Setter
public class EventRespnseWithPart {

    private Long id;
    private String eventName;
    private String place;
    private Date date;
    private String description;
    private boolean verified;
    private String image;
    private Integer countOfParticipants;






}