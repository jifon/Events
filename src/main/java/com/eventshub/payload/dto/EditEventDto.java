package com.eventshub.payload.dto;

import java.util.Date;

public class EditEventDto {

    private Long id;
    private String eventName;
    private String place;
    private Date date;
    private String description;
    private String nameOfClubOrganizer;
    private String nameOfUserOrganizer;
    private int quantityOfParticipants;

    private String image;
    private boolean verified;

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


    public String getNameOfClubOrganizer() {
        return nameOfClubOrganizer;
    }

    public void setNameOfClubOrganizer(String nameOfClubOrganizer) {
        this.nameOfClubOrganizer = nameOfClubOrganizer;
    }

    public String getNameOfUserOrganizer() {
        return nameOfUserOrganizer;
    }

    public void setNameOfUserOrganizer(String nameOfUserOrganizer) {
        this.nameOfUserOrganizer = nameOfUserOrganizer;
    }

    public int getQuantityOfParticipants() {
        return quantityOfParticipants;
    }

    public void setQuantityOfParticipants(int quantityOfParticipants) {
        this.quantityOfParticipants = quantityOfParticipants;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
