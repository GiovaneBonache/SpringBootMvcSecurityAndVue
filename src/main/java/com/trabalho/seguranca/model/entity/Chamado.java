package com.trabalho.seguranca.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "chamado")
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_chamado;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private String status;

    private LocalDateTime dataAbertura;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Chamado() {
        this.dataAbertura = LocalDateTime.now();
        this.status = "ABERTO";
    }


    public Long getId_chamado() {
        return id_chamado;
    }

    public void setId_chamado(Long id_chamado) {
        this.id_chamado = id_chamado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
