package marinalucentini.backend_w7_d1.employee.controller;

import marinalucentini.backend_w7_d1.employee.payload.EmployeeDto;
import marinalucentini.backend_w7_d1.employee.payload.EmployeeLoginDto;
import marinalucentini.backend_w7_d1.employee.payload.EmployeeLoginResponseDto;
import marinalucentini.backend_w7_d1.employee.payload.EmployeeResponseDto;
import marinalucentini.backend_w7_d1.employee.services.AuthService;
import marinalucentini.backend_w7_d1.employee.services.EmployeeService;
import marinalucentini.backend_w7_d1.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    AuthService authService;
    @PostMapping("/login")
    public EmployeeLoginResponseDto login(@RequestBody @Validated EmployeeLoginDto payload, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new EmployeeLoginResponseDto(authService.authenticateUserAndGenerateToken(payload));
    }
    @PostMapping ("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponseDto employeeResponseDto  (@RequestBody @Validated EmployeeDto employeeDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new EmployeeResponseDto( employeeService.saveEmployee(employeeDto).getId());
    }

}
