package com.example.demo.controller;
import com.example.demo.dto.ParkingDto;
import com.example.demo.entities.Parking;
import com.example.demo.exceptions.ParkingException;
import com.example.demo.service.CarServices;
import com.example.demo.service.ParkingServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/parkings")
public class ParkingController {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CarServices carServices;

    @Autowired
    ParkingServices parkingServices;

    @GetMapping(path="/", produces = "application/json")
    public ResponseEntity<List<ParkingDto>> getEmployees()
    {
        List<ParkingDto>parkingDtos = new ArrayList<>();
        List<Parking>parkings = new ArrayList<>();
        parkings = parkingServices.getAll();
        parkingDtos = convertoDtos(parkings);
        return ResponseEntity.status(HttpStatus.OK).body(parkingDtos);
    }

    @DeleteMapping(path ="/{id}",produces = "application/json")
    public ResponseEntity deleteParking(@PathVariable(value="id")Long id) throws ParkingException {
        parkingServices.deleteParking(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("PARKING HAS BEEN DELETED WITH ID"+id);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity getById(@PathVariable(value ="id")Long id) throws ParkingException {
        return ResponseEntity.status(HttpStatus.FOUND).body(convertoDto(parkingServices.getById(id)));
    }

    @GetMapping(path="/charge/{id}",produces = "application/json")
    public ResponseEntity getCharge(@PathVariable(value = "id") Long id) throws ParkingException {

            Float charges = parkingServices.chargeParking(id);

        return  ResponseEntity.status(HttpStatus.OK).body(charges);
    }

    @PostMapping("/")
    public ResponseEntity createParking(@Valid @RequestBody Parking parking) {
        ParkingDto parkingDto = convertoDto( parkingServices.createParking(parking));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingDto);
    }

    @PutMapping("/")
    public ResponseEntity updateParking(@Valid @RequestBody Parking parking) throws ParkingException{
        ParkingDto parkingDto = convertoDto(parkingServices.updateParking(parking));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(parkingDto);
    }

    private ParkingDto convertoDto(Parking parking){
        ParkingDto parkingDto = new ParkingDto();
        parkingDto = modelMapper.map(parking, ParkingDto.class);
        if (parking.getId()!=null){
            parkingDto.setId(parking.getId());
        }
        if (parking.getCar()!=null){
            parkingDto.setCarId(parking.getCar().getId());
        }
        return parkingDto;
    }

    private List<ParkingDto> convertoDtos(List<Parking> parkings){
        List<ParkingDto> parkingDtos = new ArrayList<>();
        parkings = new ArrayList<>();
        parkings.forEach(parking -> {
            ParkingDto parkingDto = modelMapper.map(parking,ParkingDto.class);
            if (parking.getId()!=null){
                parkingDto.setId(parking.getId());
            }
            if (parking.getCar()!=null){
                parkingDto.setCarId(parking.getCar().getId());
            }
            parkingDtos.add(parkingDto);
        });
        return  parkingDtos;
    }
}
