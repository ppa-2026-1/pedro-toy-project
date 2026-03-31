package com.example.demo.model.dto;

public record NewTicketDTO (
    String criadorHandle,
    String destinatarioHandle,
    String acao,
    String objeto,
    String detalhes,
    String observadores
){}