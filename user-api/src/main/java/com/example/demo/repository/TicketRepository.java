package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Ticket;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class TicketRepository {

    /*gerenciador de entidades do JPA */
    /*Converte objeto ↔ banco  - executa operações no banco*/
    private final EntityManager em; //objeto que conversa diretamente com o banco de dados

    public TicketRepository(EntityManager em){
        this.em = em;
    }

    //Buscar por ID
    public Optional<Ticket> findById(Long id){ //optional pq pode nao existir
        return Optional.ofNullable(em.find(Ticket.class, id));
    }

    //Listar todos
    public List<Ticket> findAll(){ // busca todos os Tickets
        return em.createQuery("FROM Ticket t", Ticket.class).getResultList();
    }

    //Salvar (insert ou update)
    @Transactional //significa que essa operação roda dentro de uma transação -> permite commit
    public void save(Ticket ticket){
        if(ticket.getId() == null){
            em.persist(ticket); //insert -> se nao tem id é novo
        } else {
            em.merge(ticket); //update
        }
    }

    //Deletar
    @Transactional
    public void deleteById(Long id){
        Ticket ticket = em.find(Ticket.class, id);
        if (ticket != null){
            em.remove(ticket);
        }
    }
}
