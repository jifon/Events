package com.eventshub.model;

import com.eventshub.model.enums.ClubUserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@Table(name = "club_user")
@AllArgsConstructor
@NoArgsConstructor
public class ClubUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // head or follower
    private ClubUserStatus clubUserStatus;
}
