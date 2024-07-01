package marinalucentini.backend_w7_d1.employee.services;

import marinalucentini.backend_w7_d1.employee.Employee;
import marinalucentini.backend_w7_d1.employee.payload.EmployeeLoginDto;
import marinalucentini.backend_w7_d1.employee.security.JwtTool;
import marinalucentini.backend_w7_d1.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtTool jwtTool;

    public String authenticateUserAndGenerateToken(EmployeeLoginDto payload){

        Employee employee = employeeService.findByEmail(payload.email());

        if(employee.getPassword().equals(payload.password())){

            return jwtTool.createToken(employee);
        } else {

            throw new UnauthorizedException("Credenziali non corrette!");
        }
    }
}
