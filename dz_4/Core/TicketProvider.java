package dz_4.Core;

import dz_4.Models.Ticket;
import dz_4.Services.TicketRepository;

import java.util.List;

// Класс - провайдер для работы с базой данных билетов
public class TicketProvider {
    private TicketRepository ticketRepo;

    public TicketProvider() {
        this.ticketRepo = TicketRepository.getTicketRepository();
    }

    public List<Ticket> getTickets(int routeNumber) throws RuntimeException {
        return ticketRepo.readAll(routeNumber);
    }

    public boolean updateTicketStatus(Ticket ticket) {
        ticket.setValid(false);
        return ticketRepo.update(ticket);
    }

}