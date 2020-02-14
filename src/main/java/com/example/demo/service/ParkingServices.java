package com.example.demo.service;

import com.example.demo.entities.Parking;
import com.example.demo.exceptions.ParkingException;
import com.example.demo.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingServices {
    @Autowired
    ParkingRepository parkingRepository;

    public boolean isExistedAvailableParking(){
        return parkingRepository.findByArrivalAndDeparture(null,null).isPresent();
    }

    public Parking initParking(Long id) throws ParkingException {
        if (!parkingRepository.existsById(id)){
            throw new ParkingException("no such parking with id:"+id);
        }
        Parking parking =  parkingRepository.findById(id).get();
        parking.setDeparture(null);
        parking.setArrival(null);
        parking.setCar(null);
        return parkingRepository.save(parking);
    }

    public Float chargeParking(Long id) throws ParkingException {
        if (!parkingRepository.existsById(id)){
            throw new ParkingException("no such parking with id:"+id);
        }
        Parking parking =  parkingRepository.findById(id).get();

        Date d1 = parking.getDeparture();
        Date d2 = parking.getArrival();

        Long diff = (d1.getTime()-d2.getTime())/ (60 * 60 * 1000) % 24;
        return parking.getUnitPrice()*diff;

    }

    public List<Parking> getAll(){
        return parkingRepository.findAll();
    }

    public Parking getById(Long id) throws ParkingException {
        return parkingRepository.findById(id).orElseThrow(()->new ParkingException("no parking exists with such id:"+id));
    }

    public Parking createParking(Parking parking){
        return parkingRepository.save(parking);
    }

    public void deleteParking(Long id ) throws ParkingException {

        if (!parkingRepository.existsById(id)){
            throw new ParkingException("no parking exists with such id:"+id);
        }
        parkingRepository.deleteById(id);
    }

    public Parking updateParking(Parking parking) throws ParkingException {
        if (parking.getId()==null){
            throw new ParkingException("no id find");
        }
        if (!parkingRepository.existsById(parking.getId())){
            throw new ParkingException("no parking exists with such id:"+parking.getId());
        }
        Parking p = new Parking();
        p = parking;
        return parkingRepository.save(p);
    }
}
