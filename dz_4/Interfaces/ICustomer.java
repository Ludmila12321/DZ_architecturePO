package dz_4.Interfaces;

import dz_4.Core.UserProvider;
import dz_4.Models.Ticket;
import dz_4.Models.User;

import java.util.Date;
import java.util.List;

// Интерфейс взаимодействия с UserInteraction
public interface ICustomer {
    List<Ticket> getSelectedTickets();

    void setSelectedTickets(List<Ticket> selectedTickets);

    User getUser();

    void setUser(User client);

    UserProvider getUserProvider();

    boolean buyTicket(Ticket ticket) throws RuntimeException;

    List<Ticket> searchTicket(Date date, int route) throws RuntimeException;
}