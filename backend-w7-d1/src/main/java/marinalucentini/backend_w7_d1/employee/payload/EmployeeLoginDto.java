package marinalucentini.backend_w7_d1.employee.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record EmployeeLoginDto(

        @Email(message = "valore non valido")
        String email,
        @NotEmpty(message = "Il campo è obbligatorio")
        String password) {
}
