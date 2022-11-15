package com.example.controller;


import com.example.dto.AutoDto;
import com.example.entity.Auto;
import com.example.service.AutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auto")
//@CrossOrigin("http://localhost:4200")
public class AutoController {

    @Autowired
    private AutoService autoService;

    @Autowired
    private ModelMapper modelMapper;



    @GetMapping("/showCars")
    public List<AutoDto> getAllCars(){
        List<Auto> carsList = autoService.findAll();
        return this.convertToDtoCars(carsList);
    }



    @GetMapping("/getCarById/{id_a}")
    public AutoDto getCarById(@PathVariable Integer id_a){
        Auto car = autoService.getReferenceById(id_a);
        return this.convertToDto(car);
    }



    @GetMapping("/getCarsDisp/{date}")
    public List<AutoDto> getCarsDisp(@PathVariable String date){
        //le date arrivano concatenate
        String[] dateArr = date.split("_");
        LocalDate dataI = LocalDate.parse(dateArr[0]);
        LocalDate dataF = LocalDate.parse(dateArr[1]);
        List<Auto> carsDisp = autoService.getCarsDisp(dataI, dataF);
        return convertToDtoCars(carsDisp);
    }



    @RequestMapping(value = "/addORupdCar", method = {RequestMethod.PUT, RequestMethod.POST}, consumes = {"application/json"})
    public void addORupdCar(@RequestBody AutoDto carDto){
        Auto car = convertToCar(carDto);
        autoService.save(car);
    }



    @RequestMapping(value = "/deleteCar/{id_auto}", method = RequestMethod.DELETE)
    public void delCar(@PathVariable Integer id_auto){

        autoService.deleteById(id_auto);
    }




//////////////////////////////////////    DTO CONVERTERS     ////////////////////////////////////////////
    //mappano sulla base delle proprietà
    private List<AutoDto> convertToDtoCars(List<Auto> carsList) {
        List<AutoDto> carsDto = new ArrayList<>();
        if (carsList != null) {
            //mappa attraverso uno stream sfruttando le lambda expressions
            carsDto = carsList
                    .stream()
                    .map(source -> modelMapper.map(source, AutoDto.class))
                    .collect(Collectors.toList());
        }
        return carsDto;
    }


    private AutoDto convertToDto(Auto car) {
        AutoDto carDto = null;
        if (car != null) {
            carDto =  modelMapper.map(car, AutoDto.class);
        }
        return carDto;
    }


    private Auto convertToCar(AutoDto carDto) {
        Auto car = null;
        if (carDto != null) {
            car =  modelMapper.map(carDto, Auto.class);
        }
        return car;
    }
}
