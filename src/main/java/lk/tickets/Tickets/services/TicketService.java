package lk.tickets.Tickets.services;

import jakarta.annotation.PostConstruct;
import lk.tickets.Tickets.components.Customer;
import lk.tickets.Tickets.components.TicketPool;
import lk.tickets.Tickets.components.Vendor;
import lk.tickets.Tickets.models.SystemConfig;
import lk.tickets.Tickets.models.SystemStats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author danushka
 * 2024-11-10
 */
@Service
@Slf4j
public class TicketService {
    private final SystemConfig config;
    private final TicketPool ticketPool;
    private final ExecutorService executorService;
    private final List<Vendor> vendors = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();

    public TicketService(ConfigService configService, TicketPool ticketPool) {
        this.executorService = Executors.newCachedThreadPool();
        this.ticketPool = ticketPool;
        this.config = configService.getSystemConfig();
    }

    @PostConstruct
    public void startSystem() throws InterruptedException {
        log.info("Feeding vendors to the system");
        for (int i = 0; i < 3; i++) {
            Vendor vendor = new Vendor("V" + i, ticketPool, config.getReleaseRate());
            vendors.add(vendor);
            executorService.submit(vendor);
        }
        Thread.sleep(1000);
        log.info("Feeding customers to the system");
        for (int i = 0; i < 5; i++) {
            Customer customer = new Customer("C" + i, ticketPool, config.getConsumerRate());
            customers.add(customer);
            executorService.submit(customer);
        }
    }

    public SystemStats getSystemStats() {
        return new SystemStats(ticketPool.getAvailableTickets(), ticketPool.getTotalProcessedTickets(), vendors.size(), customers.size());
    }

}
