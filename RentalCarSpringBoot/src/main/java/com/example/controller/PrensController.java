package com.example.controller;

import com.example.dto.PrenotazioneDto;
import com.example.entity.Auto;
import com.example.entity.Prenotazione;
import com.example.entity.Utente;
import com.example.service.AutoService;
import com.example.service.PrenService;
import com.example.service.UtenteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/pren")
public class PrensController {

    @Autowired
    PrenService prenService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    AutoService autoService;

    @Autowired
    UtenteService utenteService;


    @GetMapping("/showPrens")
    public List<PrenotazioneDto> getAllPrens(){
        List<Prenotazione> prenList = prenService.findAll();
        return convertToDtoPrens(prenList);
    }


    @GetMapping("/getPrenById/{id_p}")
    public PrenotazioneDto getPrenById(@PathVariable Integer id_p){
        Prenotazione pren = prenService.getReferenceById(id_p);
        return convertToDto(pren);
    }



    @GetMapping("/getUserPrens/{username}")
    public List<PrenotazioneDto> getUserPrens(@PathVariable String username){
        Utente user = utenteService.getUtenteByCodFiscale(username);
        int idU = user.getIdUtente();
        List<Prenotazione> prenList = prenService.getUserPrens(idU);
        return convertToDtoPrens(prenList);
    }



    @RequestMapping(value = "/effettuaPren", method = RequestMethod.POST, consumes = {"application/json"})
    public void effettuaPren(@RequestBody PrenotazioneDto prenDto){
        LocalDate dataI = prenDto.getDataInizio();
        LocalDate dataF = prenDto.getDataFine();
        LocalDate dataP = prenDto.getDataPren();
        int idA = prenDto.getAuto();
        Auto car = autoService.getReferenceById(idA);
        int idU = prenDto.getUtente();
        Utente user = utenteService.getReferenceById(idU);
        Boolean appr = prenDto.getApprovata();
        Prenotazione pren = new Prenotazione(dataI, dataF, dataP, appr, car, user);
        prenService.save(pren);
    }


    @RequestMapping(value = "/approvaPren", method = RequestMethod.PUT, consumes = {"application/json"})
    public void approvaPren(@RequestBody PrenotazioneDto prenDto){
        int idP = prenDto.getIdPren();
        LocalDate dataI = prenDto.getDataInizio();
        LocalDate dataF = prenDto.getDataFine();
        LocalDate dataP = prenDto.getDataPren();
        int idA = prenDto.getAuto();
        Auto car = autoService.getReferenceById(idA);
        int idU = prenDto.getUtente();
        Utente user = utenteService.getReferenceById(idU);
        Boolean appr = true;
        Prenotazione pren = new Prenotazione(idP, dataI, dataF, dataP, appr, car, user);
        prenService.save(pren);
    }


    @RequestMapping(value = "/deletePren/{id_pren}", method = RequestMethod.DELETE)
    public void delPren(@PathVariable Integer id_pren){

        prenService.deleteById(id_pren);
    }


//////////////////////////////////////    DTO CONVERTERS     ////////////////////////////////////////////
    ////mappano sulla base delle proprietà
    private List<PrenotazioneDto> convertToDtoPrens(List<Prenotazione> prensList) {
        List<PrenotazioneDto> prensDto = new ArrayList<>();
        if (prensList != null) {
            //mappa attraverso uno stream sfruttando le lambda expressions
            prensDto = prensList
                    .stream()
                    .map(source -> modelMapper.map(source, PrenotazioneDto.class))
                    .collect(Collectors.toList());
        }
        return prensDto;
    }


    private PrenotazioneDto convertToDto(Prenotazione pren) {
        PrenotazioneDto prenDto = null;
        if (pren != null) {
            prenDto =  modelMapper.map(pren, PrenotazioneDto.class);
        }
        return prenDto;
    }


    private Prenotazione convertToPren(PrenotazioneDto prenDto) {
        Prenotazione pren = null;
        if (prenDto != null) {
            pren =  modelMapper.map(prenDto, Prenotazione.class);
        }
        return pren;
    }
}
