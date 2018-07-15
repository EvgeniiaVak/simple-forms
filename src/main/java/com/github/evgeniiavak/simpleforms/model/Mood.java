package com.github.evgeniiavak.simpleforms.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

@Data
@Entity
public class Mood {

    @Id
    @GeneratedValue
    private UUID uuid;

    @GeneratedValue
    @CreationTimestamp
    private ZonedDateTime time;

    private Integer mood, focus, anxiety, irritability;

    private Duration sleep, sport;

    private Integer coffee;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mood_uuid")
    private Collection<Medicine> medicine;

    private String comment;

}
