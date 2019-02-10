package opg.softuni.employees.repositories;

import opg.softuni.employees.domain.dto.EmployeesStatisticDto;
import opg.softuni.employees.domain.models.Employee;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeRepositoryImpl extends GenericRepository implements EmployeeRepository {

    @Inject
    public EmployeeRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Employee findById(String id) {
        return (Employee) execute((em) ->
                em.createQuery("select e from Employee e where e.id = :id", Employee.class)
                        .setParameter("id", id)
                        .getSingleResult());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> findAll() {
        return (List<Employee>) execute(entityManager ->
                entityManager.createQuery("select e from Employee e", Employee.class)
                        .getResultList());
    }

    @Override
    public Employee save(Employee employee) {
        return (Employee) execute(entityManager ->{
            Employee e = entityManager.merge(employee);
            entityManager.flush();
            return e;
        });

    }

    @Override
    public Employee delete(Employee employee) {
        return (Employee) execute(entityManager ->{
            entityManager.remove(employee);
            entityManager.flush();
            return employee;
        });
    }

    @Override
    public EmployeesStatisticDto getEmployeesStatistic(){
        return (EmployeesStatisticDto) execute(entityManager ->
                entityManager.createQuery(
                        "select new opg.softuni.employees.domain.dto.EmployeesStatisticDto(sum(e.salary), avg(e.salary)) " +
                                "from Employee e"
                        , EmployeesStatisticDto.class)
                        .getSingleResult());
    }
}
