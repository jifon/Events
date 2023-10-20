package com.eventshub.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@JsonIgnoreProperties({ "subscribedClubs", "ownedClubs", "createdEvents", "participatedEvents" })
@Table(	name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    private String firstName;

    @Size(max = 20)
    private String lastName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Column( length = 64)
    private String verificationCode;

    private boolean enabled;

    @NotBlank
    @Size(max = 120)
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(name = "date_created" /*, nullable = false **/)
    private LocalDateTime dateCreated;

    private LocalDateTime dateUpdated;

    private LocalDateTime dateDeleted;

    @ManyToMany(mappedBy = "subscribers")
    private Set<Club> subscribedClubs;

    @ManyToMany(mappedBy = "headers")
    private Set<Club> ownedClubs;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    private Set<Event> createdEvents;

    @ManyToMany(mappedBy = "participants")
    private Set<Event> participatedEvents;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.enabled =false;
        this.dateCreated = LocalDateTime.now();
    }

}

