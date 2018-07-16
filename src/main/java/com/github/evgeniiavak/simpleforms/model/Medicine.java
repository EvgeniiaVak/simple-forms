package com.github.evgeniiavak.simpleforms.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class Medicine {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID mood_id;

    private String type, unit;
    private Integer amount;

}
