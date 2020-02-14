package com.example.demo.service;


import com.example.demo.entities.Car;
import com.example.demo.entities.Parking;
import com.example.demo.exceptions.CarException;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarServices {
    @Autowired
    CarRepository carRepository;
    @Autowired
    ParkingRepository parkingRepository;

    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car createCar(Car car){
        return carRepository.save(car);
    }

    public Car parkCar(Car car, Date departure) throws CarException {
        List<Parking> parkings = new ArrayList<>();
        /*find car without arrival nor departure time means available*/
        parkings = parkingRepository.findByEnergyTypeAndArrivalAndDeparture(car.getEnergyType(),null,null);
        if (parkings.size()==0){
            throw new CarException("no parking available for this car");
        }
        Parking p = parkings.get(0);
        p.setCar(car);
        p.setDeparture(departure);
        p.setArrival(java.sql.Date.valueOf(LocalDate.now()));
        car.setParking(parkings.get(0));
        parkingRepository.save(p);
        return carRepository.save(car);
    }

    public void deleteCar(Long id) throws CarException{

        if (carRepository.existsById(id)){
            throw new CarException("no car exists with such id:"+id);
        }
        carRepository.deleteById(id);
    }

    public Car updateCar(Car car ) throws CarException{
        if (car.getId()==null){
            throw new CarException("cannot find car id");
        }
        if (carRepository.existsById(car.getId())){
            throw new CarException("no car exists with such id:"+car.getId());
        }
        Car c = new Car();
        c= car;
        return carRepository.save(c);
    }

    public Car getCarById (Long id ) throws CarException {
        return carRepository.findById(id).orElseThrow(()->new CarException("no parking exists with such id:"+id));
    }
}
