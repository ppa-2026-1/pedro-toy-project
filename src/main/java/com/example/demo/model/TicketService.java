package com.example.demo.model;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.NewTicketDTO;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Ticket;
import com.example.demo.repository.entity.User;

@Service
public class TicketService {
    
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public Ticket criar(NewTicketDTO newTicket){
        
        if (newTicket.acao() == null || newTicket.acao().isEmpty()) {
            throw new IllegalArgumentException("Ação é obrigatória");
        }
        if (newTicket.objeto() == null || newTicket.objeto().isEmpty()) {
            throw new IllegalArgumentException("Objeto é obrigatório");
        }
        if(newTicket.criadorHandle() == null){
            throw new IllegalArgumentException("Criador é obrigatório");
        }

        //Buscar criador pelo handle --> Repository → busca no banco
        User criador = userRepository.findByHandle(newTicket.criadorHandle())
            .orElseThrow(() -> new IllegalArgumentException("Criador não existe"));
        
        //destinatário = criador se não informado
        User destinatario = criador;
        if(newTicket.destinatarioHandle() != null){
            destinatario = userRepository.findByHandle(newTicket.destinatarioHandle())
                .orElseThrow(() -> new IllegalArgumentException("Destinatário não existe"));
        }

        //Criar ticket
        Ticket ticket = new Ticket();
        ticket.setCriador(criador); //criador no newTicket é String e o ticket espera um USER
        ticket.setDestinatario(destinatario);
        ticket.setAcao(newTicket.acao());
        ticket.setObjeto(newTicket.objeto());
        ticket.setDetalhes(newTicket.detalhes());
        ticket.setObservadores(newTicket.observadores() != null ? newTicket.observadores() : "");

        //status incicial
        ticket.setStatus(Ticket.TicketType.PENDENTE);
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUpdatedAt(LocalDateTime.now());

        ticketRepository.save(ticket);

        return ticket;
    }

    public Ticket atualizarStatus(Long ticketId, String status, String motivo, Long responsavelId) 
    {
        Ticket ticket = ticketRepository.findById(ticketId)
            .orElseThrow(() -> new IllegalArgumentException("Ticket não encontrado"));

        Ticket.TicketType novoStatus = Ticket.TicketType.valueOf(status.toUpperCase());

        if(novoStatus == Ticket.TicketType.CANCELADO){
            if(motivo == null || motivo.isEmpty()){
                throw new IllegalArgumentException("Motivo obrigatório para cancelar ticket");
            }
            ticket.setMotivo(motivo);
        } else {
            ticket.setMotivo(null);
        }

        if(novoStatus == Ticket.TicketType.ANDAMENTO){
            if(responsavelId ==  null){
                throw new IllegalArgumentException("Responsável obrigatório ao colocar ticket em andamento");
            }
            User responsavel = userRepository.findById(responsavelId)
                .orElseThrow(() -> new IllegalArgumentException("Responsável não encontrado"));
            ticket.setResponsavel(responsavel);
        }
        LocalDateTime createdAt = ticket.getCreatedAt();

        ticket.setStatus(novoStatus);
        ticket.setUpdatedAt(LocalDateTime.now());

        ticket.setCreatedAt(createdAt);

        ticketRepository.save(ticket);
        return ticket;
    }
}
