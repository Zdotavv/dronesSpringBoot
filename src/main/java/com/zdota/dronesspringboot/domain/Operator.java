package com.zdota.dronesspringboot.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "operators")
public class Operator {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @Schema(description = "unique id of operator", pattern = "sequence")
    private Integer id;

    @Schema(description = "operators first name", example = "Alex")
    private String firstName;

    @Schema(description = "operators last name", example = "Stone")
    private String lastName;

    @Schema(description = "operators callsign", example = "Eagle")
    private String callsign;

    @Schema(description = "age of operator", maxLength = 2)
    private Integer age;

    @Schema(hidden = true)
    @OneToOne(mappedBy = "mainOperator")
    private Drone drone;

}
