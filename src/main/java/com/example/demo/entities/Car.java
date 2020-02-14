package com.example.demo.entities;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
@Table(name = "car")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="carId",nullable = false,unique = true)
    private Long id;

    @Column(name = "powerType")
    @Enumerated(EnumType.STRING)
    @NotEmpty
    private EnergyType energyType;

    @OneToOne(mappedBy = "parking" ,cascade = CascadeType.ALL)
    private Parking parking;
}
