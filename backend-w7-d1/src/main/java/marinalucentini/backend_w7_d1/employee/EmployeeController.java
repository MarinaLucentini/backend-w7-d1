package marinalucentini.backend_w7_d1.employee;

import marinalucentini.backend_w7_d1.employee.payload.EmployeeDto;
import marinalucentini.backend_w7_d1.employee.payload.EmployeeResponseDto;
import marinalucentini.backend_w7_d1.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/eployees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
public EmployeeResponseDto employeeResponseDto  (@RequestBody @Validated EmployeeDto employeeDto, BindingResult bindingResult){
if(bindingResult.hasErrors()){
    throw new BadRequestException(bindingResult.getAllErrors());
}
return new EmployeeResponseDto( employeeService.saveEmployee(employeeDto).getId());
    }
@GetMapping
    public Page<Employee> getEmployee (@RequestParam (defaultValue = "0")int page, @RequestParam (defaultValue = "5")int size, @RequestParam (defaultValue = "name")String sortBy){
return employeeService.getEmployee(page, size, sortBy);
}
@GetMapping("/{employeeId}")
    public Employee getEmployeeById (@PathVariable UUID employeeId){
return  employeeService.findById(employeeId);
}
@PutMapping("/{employeeId}")
    public Employee putEmployee (@PathVariable UUID employeeId, @RequestBody Employee employee){
return employeeService.findAndUpload(employeeId, employee);
}
@DeleteMapping("{employeeId}")
    public void deleteEmployee(@PathVariable UUID employeeId){
employeeService.findByIdAndDelete(employeeId);
}


@PostMapping("/{employeeId}/avatar")
    public Employee uploadAvatar(@PathVariable UUID employeeId, @RequestParam("avatar") MultipartFile image) throws IOException {
        String imageUrl = employeeService.uploadImage(image);
        Employee updatedEmployee = employeeService.saveImage(imageUrl, employeeId);
        return updatedEmployee;
    }
}
