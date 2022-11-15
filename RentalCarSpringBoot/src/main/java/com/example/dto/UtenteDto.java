package com.example.dto;

public class UtenteDto {
    protected int IdUtente;
    protected String codFiscale;
    protected String pwd;
    protected String nome;
    protected String cognome;
    protected Boolean isAdmin;


    public int getIdUtente() {
        return IdUtente;
    }

    public void setIdUtente(int idUtente) {
        IdUtente = idUtente;
    }

    public String getCodFiscale() {
        return codFiscale;
    }

    public void setCodFiscale(String codFiscale) {
        this.codFiscale = codFiscale;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
