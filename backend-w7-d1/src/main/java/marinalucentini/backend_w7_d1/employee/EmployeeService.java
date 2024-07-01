package marinalucentini.backend_w7_d1.employee;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import marinalucentini.backend_w7_d1.employee.payload.EmployeeDto;
import marinalucentini.backend_w7_d1.exceptions.BadRequestException;
import marinalucentini.backend_w7_d1.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class EmployeeService {
   @Autowired
    EmployeeRepository employeeRepository;
   @Autowired
    Cloudinary cloudinary;

public Employee saveEmployee(EmployeeDto employeeDto){
employeeRepository.findByEmail(employeeDto.email()).ifPresent(
employee -> {
    throw new BadRequestException("L'email " + employeeDto.email() + " è già in uso!");
}
);
Employee employeeForDb = new Employee(employeeDto.name(), employeeDto.surname(), employeeDto.email(), employeeDto.username());
employeeForDb.setUrlavatar("https://unsplash.com/it/foto/donna-in-camicia-nera-sorridente-lNNHyRbmm0o");
return employeeRepository.save(employeeForDb);
}


public Page<Employee> getEmployee(int page, int size, String sortBy){
if(size > 50) size = 50;
Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
  return   employeeRepository.findAll(pageable);
}
public Employee findById(UUID id){
return employeeRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
}
public Employee findAndUpload(UUID id, Employee employee){
    Employee found= findById(id);
    found.setEmail(employee.getEmail());
    found.setName(employee.getName());
    found.setSurname(employee.getSurname());
    found.setUrlavatar("https://unsplash.com/it/foto/donna-in-camicia-nera-sorridente-lNNHyRbmm0o");
    found.setUsername(employee.getUsername());
    return   employeeRepository.save(found);
}
    public void findByIdAndDelete(UUID employeeId) {
        Employee found = this.findById(employeeId);
        employeeRepository.delete(found);
    }
public String uploadImage(MultipartFile file) throws IOException {
    return (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
}
    public Employee saveImage(String urlImage, UUID employeeId){
        Employee employeeFound = findById(employeeId);
        employeeFound.setUrlavatar(urlImage);
        return employeeRepository.save(employeeFound);
    }
}
