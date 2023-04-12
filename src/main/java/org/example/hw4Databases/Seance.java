package org.example.hw4Databases;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Seance {
    private Long id;
    private Movie movie;
    private LocalDateTime startTime;
    private BigDecimal price;
}
