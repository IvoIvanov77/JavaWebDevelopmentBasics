package opg.softuni.employees.repositories;

import opg.softuni.employees.domain.dto.EmployeesStatisticDto;
import opg.softuni.employees.domain.models.Employee;

public interface EmployeeRepository extends Repository<String, Employee> {
    EmployeesStatisticDto getEmployeesStatistic();
}
