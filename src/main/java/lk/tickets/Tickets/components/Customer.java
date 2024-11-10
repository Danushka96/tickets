package lk.tickets.Tickets.components;

import lk.tickets.Tickets.models.Ticket;
import lombok.extern.slf4j.Slf4j;

/**
 * @author danushka
 * 2024-11-10
 */
@Slf4j
public class Customer implements Runnable{
    private final String customerId;
    private final TicketPool ticketPool;
    private final int retrievalRate;

    public Customer(String customerId, TicketPool ticketPool, int customerRetrievalRate) {
        this.customerId = customerId;
        this.ticketPool = ticketPool;
        this.retrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        try {
            Ticket ticket = ticketPool.removeTicket();
            log.info("Customer {} purchased ticket: {}", customerId, ticket);
            Thread.sleep(retrievalRate);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
