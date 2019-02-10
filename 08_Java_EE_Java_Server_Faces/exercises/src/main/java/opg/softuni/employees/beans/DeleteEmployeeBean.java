package opg.softuni.employees.beans;

import opg.softuni.employees.services.EmployeeService;

import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "deleteAction")
public class DeleteEmployeeBean {
    private final EmployeeService employeeService;

    @Inject
    public DeleteEmployeeBean(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void delete(String id){
        this.employeeService.deleteEmployee(id);
    }


}
