package dz_4.Services;

import dz_4.Interfaces.ITicketRepo;
import dz_4.Models.Ticket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketRepository implements ITicketRepo {
    private static TicketRepository ticketRepository;
    private final List<Ticket> tickets;

    private TicketRepository() {
        //здесь симуляция работы с БД
        tickets = new ArrayList<>();
        String strDate = "2022-10-27";
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        Date date;
        try {
            date = sdf.parse( strDate );
        } catch (Exception ex) {
            date = null;
        }
        generateTickets( 1, 6, 10, date );
        generateTickets( 2, 4, 15, date );
    }

    public static TicketRepository getTicketRepository() {
        if (ticketRepository == null) {
            ticketRepository = new TicketRepository();
        }
        return ticketRepository;
    }

    @Override
    public boolean create(Ticket ticket) {
        //функционал не используется
        tickets.add( ticket );
        return true;
    }

    @Override
    public List<Ticket> readAll(int routeNumber) throws RuntimeException {
        List<Ticket> routeTickets = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.getRouteNumber() == routeNumber && ticket.getValid() == true) {
                routeTickets.add( ticket );
            }
        }
        if (routeTickets.isEmpty()) {
            throw new RuntimeException( "Билетов нет." );
        }
        return tickets;
    }

    @Override
    public boolean update(Ticket ticket) {
        for (Ticket tick : tickets) {
            if (tick.equals( ticket )) {
                tickets.remove( tick );
                tickets.add( ticket );
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Ticket ticket) {
        //функционал не используется
        if (tickets.contains( ticket )) {
            tickets.remove( ticket );
            return true;
        }
        return false;
    }

    private void generateTickets(int roureNumber, int countPlaces, int price, Date date) {
        for (int i = 1; i <= countPlaces; i++) {
            tickets.add( new Ticket( roureNumber, i, price, date, true ) );
        }
    }
}