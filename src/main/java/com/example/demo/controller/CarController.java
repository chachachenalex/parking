package com.example.demo.controller;
import com.example.demo.dto.CarDto;
import com.example.demo.dto.ParkingDto;
import com.example.demo.entities.Car;
import com.example.demo.entities.Parking;
import com.example.demo.exceptions.CarException;
import com.example.demo.exceptions.ParkingException;
import com.example.demo.service.CarServices;
import com.example.demo.service.ParkingServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/cars")
public class CarController {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CarServices carServices;

    @Autowired
    ParkingServices parkingServices;

    @GetMapping(path="/", produces = "application/json")
    public ResponseEntity<List<CarDto>> getEmployees()
    {
        List<CarDto>carDtos = new ArrayList<>();
        List<Car>cars = new ArrayList<>();
        cars = carServices.getAll();
        carDtos = convertoDtos(cars);
        return ResponseEntity.status(HttpStatus.OK).body(carDtos);
    }

    @PostMapping("/")
    public ResponseEntity createCar(@Valid @RequestBody Car car) {
        CarDto carDto = convertoDto( carServices.createCar(car));
        return ResponseEntity.status(HttpStatus.OK).body(carDto);
    }

    @PostMapping("/parkcar")
    public ResponseEntity parkCar(@Valid @RequestBody Car car,@RequestParam(name ="departure") Date departure) throws CarException {
        CarDto carDto = convertoDto(carServices.parkCar(car,departure));
        return ResponseEntity.status(HttpStatus.OK).body(carDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCarById (@PathVariable(name = "id") Long id) throws CarException {
        carServices.deleteCar(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("PARKING HAS BEEN DELETED WITH ID"+id);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity getCarById(@PathVariable(name ="id")Long id) throws CarException{
        return ResponseEntity.status(HttpStatus.FOUND).body(convertoDto(carServices.getCarById(id)));
    }

    @PutMapping("/")
    public ResponseEntity updateCar(@Valid @RequestBody Car car) throws CarException {
        CarDto carDto = convertoDto(carServices.updateCar(car));
        return ResponseEntity.status(HttpStatus.OK).body(carDto);
    }
    private CarDto convertoDto(Car car){
        CarDto carDto = new CarDto();
        carDto = modelMapper.map(car, CarDto.class);
        if (car.getId()!=null){
            carDto.setId(car.getId());
        }
        if (car.getParking()!=null){
            carDto.setParkingId(car.getParking().getId());
        }
        return carDto;
    }

    private List<CarDto> convertoDtos(List<Car> cars){
        List<CarDto> carDtos = new ArrayList<>();
        cars = new ArrayList<>();
        cars.forEach(car -> {
            CarDto carDto = modelMapper.map(car,CarDto.class);
            if (car.getId()!=null){
                carDto.setId(car.getId());
            }
            if (car.getParking()!=null){
                carDto.setParkingId(car.getParking().getId());
            }
            carDtos.add(carDto);
        });
        return  carDtos;
    }
}
