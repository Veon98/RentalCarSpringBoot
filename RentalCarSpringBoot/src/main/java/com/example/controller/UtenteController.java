package com.example.controller;

import com.example.dto.UtenteDto;
import com.example.entity.Utente;
import com.example.service.UtenteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/utente")
//@CrossOrigin("http://localhost:4200")
public class UtenteController {

    //private static final Logger logger = Logger.getLogger("Customer detail service: ");

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private ModelMapper modelMapper;



    @GetMapping("/showUsers")
    public List<UtenteDto> getAllUsers(){
        List<Utente> usersList = utenteService.findAll();
        return this.convertToDtoUsers(usersList);
    }



    @GetMapping("/getUserById/{id_u}")
    public UtenteDto getUserById(@PathVariable Integer id_u){
        Utente user = utenteService.getReferenceById(id_u);
        return this.convertToDto(user);
    }



    @GetMapping("/getUserByCodFis/{codFis}")
    public UtenteDto getUserByCodFis(@PathVariable String codFis){
        Utente user = utenteService.getUtenteByCodFiscale(codFis);
        return convertToDto(user);
    }



    @RequestMapping(value = "/addORupdUser", method = {RequestMethod.PUT, RequestMethod.POST}, consumes = {"application/json"})
    public void addORupdUser(@RequestBody UtenteDto userDto){
        Utente user = convertToUser(userDto);
        //evita che la pwd venga ricfrata nel momento della modifica dell'utente
        if (user.getIdUtente()==0) {
            user.setPwd(passwordEncoder.encode(user.getPwd()));
        }
        utenteService.save(user);
    }



    @RequestMapping(value = "/deleteUser/{id_utente}", method = RequestMethod.DELETE)
    public void delUser(@PathVariable Integer id_utente){

        utenteService.deleteById(id_utente);
    }




//////////////////////////////////////    DTO CONVERTERS     ////////////////////////////////////////////
    //mappano sulla base delle proprietà
    private List<UtenteDto> convertToDtoUsers(List<Utente> usersList) {
        List<UtenteDto> usersDto = new ArrayList<>();
        if (usersList != null) {
            //mappa attraverso uno stream sfruttando le lambda expressions
            usersDto = usersList
                    .stream()
                    .map(source -> modelMapper.map(source, UtenteDto.class))
                    .collect(Collectors.toList());
        }
        return usersDto;
    }


    private UtenteDto convertToDto(Utente user) {
        UtenteDto userDto = null;
        if (user != null) {
            userDto =  modelMapper.map(user, UtenteDto.class);
        }
        return userDto;
    }


    private Utente convertToUser(UtenteDto userDto) {
        Utente user = null;
        if (userDto != null) {
            user =  modelMapper.map(userDto, Utente.class);
        }
        return user;
    }
}
