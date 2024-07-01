package marinalucentini.backend_w7_d1.device;

import jakarta.validation.constraints.NotEmpty;

public record DeviceUploadDto(
        @NotEmpty(message = "Il campo è obbligatorio")
        String stateDevice,
        @NotEmpty(message = "Il campo è obbligatorio")
        String employeeId
) {
}
