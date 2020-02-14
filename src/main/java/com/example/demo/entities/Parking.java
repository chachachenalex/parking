package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Data
@Table(name = "parking")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="parkingId",unique = true, nullable = false)
    private Long id;

    @Column(name = "powerType")
    @Enumerated(EnumType.STRING)
    @NotEmpty
    private EnergyType energyType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_Id",referencedColumnName = "carId")
    private Car car;

    @Column(name ="arrival")
    private Date arrival;

    @Column(name ="departure")
    private Date departure;

    @Column(name ="unitPrice")
    @NotEmpty
    private Float unitPrice;
}
