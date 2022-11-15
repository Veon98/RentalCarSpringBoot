package com.example.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "prenotazione")
public class Prenotazione implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  //generazione chiave primaria automatica
    @Column(name="id_pren")
    //@Size(min=10, max=10)
    protected int idPren;


    @Column(name = "data_inizio")
    private LocalDate dataInizio;


    @Column(name = "data_fine")
    private LocalDate dataFine;


    @Column(name = "data_prenotazione")
    private LocalDate dataPren;


    @Column(name = "approvazione")
    //@Size(min=1, max=1)
    private Boolean isApprovata;



    @ManyToOne
    @JoinColumn(name = "id_auto", referencedColumnName = "id_auto")  //foreign key
    private Auto auto;  //proprità che mappa la relazione con l'entità auto


    @ManyToOne
    @JoinColumn(name = "id_utente", referencedColumnName = "id_utente")  //foreign key
    private Utente utente;   //proprità che mappa la relazione con l'entità utente


    public Prenotazione(){}

    public Prenotazione(LocalDate dataInizio, LocalDate dataFine, LocalDate dataPren) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.dataPren = dataPren;
    }


    public Prenotazione(LocalDate dataInizio, LocalDate dataFine, LocalDate dataPren, Boolean isApprovata, Auto auto, Utente utente) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.dataPren = dataPren;
        this.isApprovata = isApprovata;
        this.auto = auto;
        this.utente = utente;
    }


    public Prenotazione(int idPren, LocalDate dataInizio, LocalDate dataFine, LocalDate dataPren, Boolean isApprovata, Auto auto, Utente utente) {
        this.idPren = idPren;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.dataPren = dataPren;
        this.isApprovata = isApprovata;
        this.auto = auto;
        this.utente = utente;
    }

    public int getIdPren() {
        return idPren;
    }

    public void setIdPren(int idPren) {
        this.idPren = idPren;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate  getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public LocalDate getDataPren() {
        return dataPren;
    }

    public void setDataPren(LocalDate dataPren) {
        this.dataPren = dataPren;
    }

    public Boolean getApprovata() {
        return isApprovata;
    }

    public void setApprovata(Boolean approvata) {
        isApprovata = approvata;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

}


