package com.nas.employeeservice.repository;

import com.nas.employeeservice.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class EmployeeRepository {

    private List<Employee> employeeList = new ArrayList<>();

    public Employee add(Employee employee){
        employeeList.add(employee);
        return employee;
    }

    public Employee findById(Long id){
        return employeeList.stream()
                .filter(employee -> employee.id().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public String delete(Long id){
        employeeList = employeeList.stream()
                .filter(employee -> !Objects.equals(employee.id(), id)).toList();
        return "Deleted Successfully";
    }

    public List<Employee> findAll(){
        return employeeList;
    }

    public List<Employee> findByDepartment(Long depId){
        return employeeList.stream().filter(employee -> employee.departmentId().equals(depId)).toList();
    }

}
