package opg.softuni.employees.services;

import opg.softuni.employees.domain.dto.CreateEmployeeDto;
import opg.softuni.employees.domain.dto.EmployeeViewDto;
import opg.softuni.employees.domain.dto.EmployeesStatisticDto;

import java.util.List;

public interface EmployeeService {
    boolean createEmployee(CreateEmployeeDto employeeDto);

    boolean deleteEmployee(String id);

    List<EmployeeViewDto> listAllEmployees();

    EmployeesStatisticDto getEmployeesStatistic();
}
