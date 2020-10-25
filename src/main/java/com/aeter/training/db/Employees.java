package com.aeter.training.db;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "EMPLOYEES")
public class Employees {
    private Long employeeId;

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
@Id
    public Long getEmployeeId() {
        return employeeId;
    }
}
