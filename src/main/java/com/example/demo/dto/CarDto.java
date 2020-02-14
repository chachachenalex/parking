package com.example.demo.dto;
import com.example.demo.entities.EnergyType;
import lombok.Data;
import java.io.Serializable;

@Data
public class CarDto implements Serializable {
    private static final long serialVersionUID = 4865903039190150223L;
    private Long Id;
    private EnergyType energyType;
    private Long parkingId;
}
