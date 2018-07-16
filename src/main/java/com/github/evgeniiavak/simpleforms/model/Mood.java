package com.github.evgeniiavak.simpleforms.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

@Data
@Entity
public class Mood implements Identifiable<UUID> {

    @Id
    @GeneratedValue
    private UUID id;

    @GeneratedValue
    @CreationTimestamp
    private ZonedDateTime time;

    private Integer joy, focus, anxiety, irritability;

    private Duration sleep, sport;

    private Integer coffee;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mood_id")
    private Collection<Medicine> medicine;

    private String comment;

}
