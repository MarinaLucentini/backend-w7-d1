package marinalucentini.backend_w7_d1.exceptions;

import java.time.LocalDateTime;

public record ErrorDto(String massage, LocalDateTime localDateTime) {
}
