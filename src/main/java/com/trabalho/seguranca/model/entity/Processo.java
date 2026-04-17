package com.trabalho.seguranca.model.entity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nomeProcesso;
    private String numeroProcesso;
    private String tipoProcesso;
    private String areaDireito;

    private String tribunal;
    private String vara;

    private String cidade;
    private String estado;

    private String faseProcesso;

    private String nomeCliente;

    private LocalDate proximaAudiencia;

    private LocalTime horaInicioAudiencia;

    private LocalTime horaFimAudiencia;

    private String responsavel;

    @Column(length = 2000)
    private String observacoes;

    public Processo(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProcesso() {
        return nomeProcesso;
    }

    public void setNomeProcesso(String nomeProcesso) {
        this.nomeProcesso = nomeProcesso;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getTipoProcesso() {
        return tipoProcesso;
    }

    public void setTipoProcesso(String tipoProcesso) {
        this.tipoProcesso = tipoProcesso;
    }

    public String getAreaDireito() {
        return areaDireito;
    }

    public void setAreaDireito(String areaDireito) {
        this.areaDireito = areaDireito;
    }

    public String getTribunal() {
        return tribunal;
    }

    public void setTribunal(String tribunal) {
        this.tribunal = tribunal;
    }

    public String getVara() {
        return vara;
    }

    public void setVara(String vara) {
        this.vara = vara;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFaseProcesso() {
        return faseProcesso;
    }

    public void setFaseProcesso(String faseProcesso) {
        this.faseProcesso = faseProcesso;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public LocalDate getProximaAudiencia() {
        return proximaAudiencia;
    }

    public void setProximaAudiencia(LocalDate proximaAudiencia) {
        this.proximaAudiencia = proximaAudiencia;
    }

    public LocalTime getHoraInicioAudiencia() {
        return horaInicioAudiencia;
    }

    public void setHoraInicioAudiencia(LocalTime horaInicioAudiencia) {
        this.horaInicioAudiencia = horaInicioAudiencia;
    }

    public LocalTime getHoraFimAudiencia() {
        return horaFimAudiencia;
    }

    public void setHoraFimAudiencia(LocalTime horaFimAudiencia) {
        this.horaFimAudiencia = horaFimAudiencia;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
