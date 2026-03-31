package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.MediaType; 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TicketService;
import com.example.demo.model.dto.NewTicketDTO;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Ticket;
import com.example.demo.repository.entity.User;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {
    
    private final TicketService ticketService;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketController(TicketService ticketService, TicketRepository ticketRepository, UserRepository userRepository){
        this.ticketRepository = ticketRepository;
        this.ticketService = ticketService;
        this.userRepository = userRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Ticket criar (@RequestBody NewTicketDTO newTicket){
        return ticketService.criar(newTicket);
    }

    /*produces = deixar explícito que retorna JSON */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ticket> listar() {
        return ticketRepository.findAll();
    }

    @PatchMapping("/{id}/status") //atualização parcial (no caso, só o status do ticket)
    public Ticket atualizarStatus(
        @PathVariable Long id, //Pega o {id} da URL
        @RequestParam String status, //?status=ANDAMENTO
        @RequestParam(required = false) String motivo, //required = false → pode vir ou não
        @RequestParam(required = false) Long responsavelId) { //Só usado quando for ANDAMENTO
        return ticketService.atualizarStatus(id, status, motivo, responsavelId);
    }
    
}
