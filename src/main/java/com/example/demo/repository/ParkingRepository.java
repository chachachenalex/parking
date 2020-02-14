package com.example.demo.repository;
import com.example.demo.entities.EnergyType;
import com.example.demo.entities.Parking;
import org.springframework.data.repository.CrudRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ParkingRepository extends CrudRepository<Parking,Long> {
        Optional<List<ParkingRepository>> findByArrivalAndDeparture(Date arrival, Date departure);

        List<Parking>findAll();

        List<Parking>findByEnergyTypeAndArrivalAndDeparture(EnergyType energyType,Date arrival,Date departure);
}
