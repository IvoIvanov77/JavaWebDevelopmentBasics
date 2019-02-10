package opg.softuni.employees.domain.dto;

import java.math.BigDecimal;

public class EmployeesStatisticDto {
    private BigDecimal totalMoney;

    private Double averageSalary;

    public EmployeesStatisticDto() {
    }

    public EmployeesStatisticDto(BigDecimal totalMoney, Double averageSalary) {
        this.totalMoney = totalMoney;
        this.averageSalary = averageSalary;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(Double averageSalary) {
        this.averageSalary = averageSalary;
    }
}
