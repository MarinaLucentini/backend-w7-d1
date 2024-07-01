package marinalucentini.backend_w7_d1.device;

import marinalucentini.backend_w7_d1.device.entities.Device;
import marinalucentini.backend_w7_d1.device.enums.StateDevice;
import marinalucentini.backend_w7_d1.device.enums.TypeDevice;
import marinalucentini.backend_w7_d1.employee.Employee;
import marinalucentini.backend_w7_d1.employee.EmployeeService;
import marinalucentini.backend_w7_d1.exceptions.BadRequestException;
import marinalucentini.backend_w7_d1.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeviceService {
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    EmployeeService employeeService;
    public TypeDevice typeDevice (String deviceDto){
        try{
           return   TypeDevice.valueOf(deviceDto.toUpperCase());

        } catch (IllegalArgumentException ex){
            throw new BadRequestException("Il tipo del dispositivo deve essere SMARTPHONE,TABLET O LAPTOP!");
        }
    }
    public StateDevice stateDevice (String deviceDto){
        try{
           return   StateDevice.valueOf(deviceDto.toUpperCase());

        } catch (IllegalArgumentException ex){
            throw new BadRequestException("Lo stato del dispositivo deve essere AVAIBLE, ASSIGNED, MAINTENANCE, DISMISSED!");
        }
    }
    public Device saveDevice (DeviceDto deviceDto){
            Device newDevice = new Device(typeDevice(deviceDto.typeDevice()), stateDevice(deviceDto.typeDevice()));
            return deviceRepository.save(newDevice);
    }
    public Page<Device> getEmployee(int page, int size){
        if(size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size);
        return   deviceRepository.findAll(pageable);
    }
    public Device findById(UUID id){
        return deviceRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
    public Device findAndUpload(UUID deviceId, DeviceUploadDto device){
        Device found= findById(deviceId);
        Employee employeefound = employeeService.findById(UUID.fromString(device.employeeId()));

        found.setStateDevice(stateDevice(device.stateDevice()));
        found.setEmployee(employeefound);
        return   deviceRepository.save(found);
    }
    public void findByIdAndDelete(UUID deviceId) {
       Device found = this.findById(deviceId);
        deviceRepository.delete(found);
    }

}
