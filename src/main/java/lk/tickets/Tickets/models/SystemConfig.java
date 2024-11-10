package lk.tickets.Tickets.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author danushka
 * 2024-11-10
 */
@Data
@NoArgsConstructor
public class SystemConfig {
    private int total;
    private int releaseRate;
    private int consumerRate;
    private int maxCapacity;

    @Builder
    public SystemConfig(int total, int releaseRate, int consumerRate, int maxCapacity) {
        this.total = total;
        this.releaseRate = releaseRate;
        this.consumerRate = consumerRate;
        this.maxCapacity = maxCapacity;
    }
}
