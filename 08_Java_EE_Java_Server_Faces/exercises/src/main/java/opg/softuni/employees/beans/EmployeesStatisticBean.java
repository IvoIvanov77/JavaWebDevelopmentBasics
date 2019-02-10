package opg.softuni.employees.beans;

import opg.softuni.employees.domain.dto.EmployeesStatisticDto;
import opg.softuni.employees.services.EmployeeService;

import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "employeesStatistic")
public class EmployeesStatisticBean {
    private final EmployeeService employeeService;

    @Inject
    public EmployeesStatisticBean(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public EmployeesStatisticDto getStatistic(){
        return this.employeeService.getEmployeesStatistic();
    }


}
