package opg.softuni.employees.beans;

import opg.softuni.employees.domain.dto.CreateEmployeeDto;
import opg.softuni.employees.services.EmployeeService;
import opg.softuni.employees.util.ApplicationUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;

@Named(value = "createEmployee")
public class CreateEmployeeBean {
    private final EmployeeService employeeService;

    private final CreateEmployeeDto createEmployeeDto;

    @Inject
    public CreateEmployeeBean(EmployeeService employeeService, CreateEmployeeDto createEmployeeDto) {
        this.employeeService = employeeService;
        this.createEmployeeDto = createEmployeeDto;
    }

    public void setFirstName(String firstName) {
        this.createEmployeeDto.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        this.createEmployeeDto.setLastName(lastName);
    }

    public void setPosition(String position) {
        this.createEmployeeDto.setPosition(position);
    }

    public void setSalary(BigDecimal salary) {
        this.createEmployeeDto.setSalary(salary);
    }

    public void setAge(Short age) {
        this.createEmployeeDto.setAge(age);
    }

    public String getFirstName() {
        return this.createEmployeeDto.getFirstName();
    }

    public String getLastName() {
        return this.createEmployeeDto.getLastName();
    }

    public String getPosition() {
        return this.createEmployeeDto.getPosition();
    }

    public BigDecimal getSalary() {
        return this.createEmployeeDto.getSalary();
    }

    public Short getAge() {
        return this.createEmployeeDto.getAge();
    }

    public void createEmployee(){
        this.employeeService.createEmployee(this.createEmployeeDto);
    }
}
