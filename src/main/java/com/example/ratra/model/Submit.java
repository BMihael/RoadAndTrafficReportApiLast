package com.example.ratra.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="submits")
public class Submit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private String description;
    @Column
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private SubmitType type;

    @JsonManagedReference
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "submit",
            cascade = CascadeType.ALL)
    private List<ImageFile> images;
}
