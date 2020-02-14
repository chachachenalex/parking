package com.example.demo.repository;

import com.example.demo.entities.Car;
import com.example.demo.entities.EnergyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    Optional<List<Car>> findByEnergyType(EnergyType energyType);
}
