package lk.tickets.Tickets.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author danushka
 * 2024-11-10
 */
@Data
@AllArgsConstructor
public class SystemStats {
    private int availableTickets;
    private int totalProcessedTickets;
    private int activeVendors;
    private int activeCustomers;
}
