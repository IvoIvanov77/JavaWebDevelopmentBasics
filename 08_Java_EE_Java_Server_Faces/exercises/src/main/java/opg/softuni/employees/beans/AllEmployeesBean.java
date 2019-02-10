package opg.softuni.employees.beans;

import opg.softuni.employees.domain.dto.EmployeeViewDto;
import opg.softuni.employees.services.EmployeeService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "allEmployees")
public class AllEmployeesBean {
    private final EmployeeService employeeService;

    @Inject
    public AllEmployeesBean(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<EmployeeViewDto> getAllEmployees() {
        return this.employeeService.listAllEmployees();
    }


}
