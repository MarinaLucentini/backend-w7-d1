package marinalucentini.backend_w7_d1.device;

import jakarta.validation.constraints.NotEmpty;

public record DeviceDto(
        @NotEmpty (message = "Il tipo del dispositivo è obbligatorio")
        String typeDevice,
        @NotEmpty(message = "Lo stato del dispositivo è obbligatorio")
        String stateDevice
) {
}
