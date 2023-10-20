package com.eventshub.model;


//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    private List<User> headsAndFollowers;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Club> clubs;
}
