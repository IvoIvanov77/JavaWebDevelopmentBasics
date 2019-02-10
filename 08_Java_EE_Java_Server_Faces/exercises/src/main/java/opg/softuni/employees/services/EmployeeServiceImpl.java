package opg.softuni.employees.services;

import opg.softuni.employees.domain.dto.CreateEmployeeDto;
import opg.softuni.employees.domain.dto.EmployeeViewDto;
import opg.softuni.employees.domain.dto.EmployeesStatisticDto;
import opg.softuni.employees.domain.models.Employee;
import opg.softuni.employees.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    @Inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean createEmployee(CreateEmployeeDto employeeDto){
        try {
            this.employeeRepository.save(modelMapper.map(employeeDto, Employee.class));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteEmployee(String id){
        Employee e = this.employeeRepository.findById(id);
        try{
            this.employeeRepository.delete(e);
        }catch (Exception ex){
            return false;
        }
        return true;
    }

    @Override
    public List<EmployeeViewDto> listAllEmployees(){
        return this.employeeRepository.findAll()
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeViewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeesStatisticDto getEmployeesStatistic(){
        return this.employeeRepository.getEmployeesStatistic();
    }


}
