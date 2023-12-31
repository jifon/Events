package com.eventshub.payload.dto;

import java.util.Date;

public class EventDto {
        //private Long id;
        private String eventName;
        private String place;
        private String date;
        private String description;

        public EventDto(String eventName, String place, String date, String description) {
                this.eventName = eventName;
                this.place = place;
                this.date = date;
                this.description = description;
        }

        //        private boolean verified;
//        private String image;
//        private String nameOfClubOrganizer;
//        private String nameOfUserOrganizer;
//        private int quantityOfParticipants;



//        public Long getId() {
//                return id;
//        }
//
//        public void setId(Long id) {
//                this.id = id;
//        }

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

//        public Date getDate() {
//                return date;
//        }
//
//        public void setDate(Date date) {
//                this.date = date;
//        }


        public String getDate() {
                return date;
        }

        public void setDate(String date) {
                this.date = date;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public void setId(Long id) {
        }

//        public boolean isVerified() {
//                return verified;
//        }
//
//        public void setVerified(boolean verified) {
//                this.verified = verified;
//        }
//
//        public String getImage() {
//                return image;
//        }
//
//        public void setImage(String image) {
//                this.image = image;
//        }
//
//        public String getNameOfClubOrganizer() {
//                return nameOfClubOrganizer;
//        }
//
//        public void setNameOfClubOrganizer(String nameOfClubOrganizer) {
//                this.nameOfClubOrganizer = nameOfClubOrganizer;
//        }
//
//        public String getNameOfUserOrganizer() {
//                return nameOfUserOrganizer;
//        }
//
//        public void setNameOfUserOrganizer(String nameOfUserOrganizer) {
//                this.nameOfUserOrganizer = nameOfUserOrganizer;
//        }
//
//        public int getQuantityOfParticipants() {
//                return quantityOfParticipants;
//        }
//
//        public void setQuantityOfParticipants(int quantityOfParticipants) {
//                this.quantityOfParticipants = quantityOfParticipants;
//        }
}
