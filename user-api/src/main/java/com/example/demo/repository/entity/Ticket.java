package com.example.demo.repository.entity;

import java.time.LocalDateTime;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    public enum TicketType {
        PENDENTE,
        ANDAMENTO,
        RESOLVIDO,
        CANCELADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"email", "password", "profile", "roles"})
    @ManyToOne //Muitos para um -> vários tickets podem ter o mesmo criador
    @JoinColumn(name = "criador_id", nullable = false)
    private User criador;

    @JsonIgnoreProperties({"email", "password", "profile", "roles"})
    @ManyToOne
    @JoinColumn(name = "destinatario_id", nullable = false)
    private User destinatario;

    @JsonIgnoreProperties({"email", "password", "profile", "roles"})
    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private User responsavel;

    @Column(columnDefinition = "TEXT")
    private String observadores;

    private String acao;
    private String objeto;

    @Column(columnDefinition = "TEXT")
    private String detalhes;

    @Column(columnDefinition = "TEXT")
    private String motivo;

    @Enumerated(EnumType.STRING) 
    private TicketType status; 

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    public Long getId() {return id;}

    public User getCriador() {return criador;}
    public void setCriador(User criador) {
        this.criador = criador;
    }

    public User getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(User destinatario) {
        this.destinatario = destinatario;
    }

    public User getResponsavel() {return responsavel;}
    public void setResponsavel(User responsavel) {
        this.responsavel = responsavel;
    }

    public String getObservadores() {
        return observadores;
    }
    public void setObservadores(String observadores) {
        this.observadores = observadores;
    }

    public String getAcao() {return acao;}
    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getObjeto() {return objeto;}
    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getDetalhes() {return detalhes;}
    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getMotivo() {return motivo;}
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public TicketType getStatus() {return status;}
    public void setStatus(TicketType status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}