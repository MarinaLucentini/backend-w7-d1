package marinalucentini.backend_w7_d1.employee.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record EmployeeDto(
        @NotEmpty(message = "Il nome è un campo obbligatorio")
                @Size(min = 3, max= 40, message = "Il nome deve essere con un numero di caratteri compreso tra 3 e 40")
        String name,
        @NotEmpty(message = "Il cognome è un campo obbligatorio")
                @Size(min = 3, max = 40, message = "Il cognome dev essere con un numero di caratteri compreso tra 3 e 40")
        String surname,
                 @Email(message = "Valore non valido")
                 String email,
                 @NotEmpty(message = "Il campo è obbligatorio")
                         @Size(min = 4, max = 30, message = "Lo username deve avere un numero di caratteri compreso tra 4 e 30")
                 String username,
        @NotEmpty(message = "Il campo è obbligatorio")
                @Size (min = 4, max = 30, message = "La password deve avere un numero di caratteri compreso tra 4 e 30")
        String password
) {
}
