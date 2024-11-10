package lk.tickets.Tickets.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author danushka
 * 2024-11-10
 */
@Data
@AllArgsConstructor
public class Ticket {
    private UUID id;
    private LocalDateTime createdAt;
    private double price;

    public static Ticket create(double price) {
        return new Ticket(UUID.randomUUID(), LocalDateTime.now(), price);
    }
}
