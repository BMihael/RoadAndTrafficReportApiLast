package com.example.ratra.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String phone;
    @Column
    private String address;
    @Column
    private String postcode;
    @Column
    private String email;
    @Column
    private String country;
    @Column
    @Lob
    private byte[] profilePictureData;

    @JsonBackReference
    @OneToOne(mappedBy = "userSettings")
    private User user;
}
