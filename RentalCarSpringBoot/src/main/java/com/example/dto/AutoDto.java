package com.example.dto;

public class AutoDto {
    protected int idAuto;
    protected String targa;
    protected String marca;
    protected String modello;
    protected Boolean isDisponibile;



    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public Boolean getDisponibile() {
        return isDisponibile;
    }

    public void setDisponibile(Boolean disponibile) {
        isDisponibile = disponibile;
    }
}
