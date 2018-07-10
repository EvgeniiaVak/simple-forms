package com.github.evgeniiavak.simpleforms.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;
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

    private int mood, focus, anxiety;

}
