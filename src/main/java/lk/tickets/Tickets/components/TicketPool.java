package lk.tickets.Tickets.components;

import lk.tickets.Tickets.models.SystemConfig;
import lk.tickets.Tickets.models.Ticket;
import lk.tickets.Tickets.services.ConfigService;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author danushka
 * 2024-11-10
 */
@Component
public class TicketPool {
    private final BlockingQueue<Ticket> tickets;
    private final int maxCapacity;
    private final AtomicInteger totalProcessedTickets = new AtomicInteger(0);

    public TicketPool(ConfigService configService) {
        SystemConfig config = configService.getSystemConfig();
        this.maxCapacity = config.getMaxCapacity();
        this.tickets = new LinkedBlockingQueue<>(maxCapacity);
    }

    public void addTicket(Ticket ticket) throws InterruptedException {
        if (tickets.size() < maxCapacity) {
            tickets.put(ticket);
            totalProcessedTickets.incrementAndGet();
        }
    }

    public Ticket removeTicket() throws InterruptedException {
        return tickets.take();
    }

    public int getAvailableTickets() {
        return tickets.size();
    }

    public int getTotalProcessedTickets() {
        return totalProcessedTickets.get();
    }
}
