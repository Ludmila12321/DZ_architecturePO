package dz_4.Interfaces;

import dz_4.Models.Ticket;

import java.util.List;

public interface ITicketRepo {

    boolean create(Ticket ticket);
    List<Ticket> readAll(int routeNumber);
    boolean update(Ticket ticket);
    boolean delete(Ticket ticket);
}