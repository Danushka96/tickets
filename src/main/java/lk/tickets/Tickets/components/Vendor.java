package lk.tickets.Tickets.components;

import lk.tickets.Tickets.models.Ticket;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author danushka
 * 2024-11-10
 */
@Slf4j
public class Vendor implements Runnable {
    private final String vendorId;
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final Random random = new Random();

    public Vendor(String vendorId, TicketPool ticketPool, int ticketReleaseRate) {
        this.vendorId = vendorId;
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        try {
            double price = 50 + random.nextDouble() * 150;
            Ticket ticket = Ticket.create(price);
            ticketPool.addTicket(ticket);
            log.info("Vendor {} released ticket: {}", vendorId, ticket);
            Thread.sleep(ticketReleaseRate);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
