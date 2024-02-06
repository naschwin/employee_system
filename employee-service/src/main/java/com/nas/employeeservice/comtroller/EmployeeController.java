package com.nas.employeeservice.comtroller;

import com.nas.employeeservice.model.Employee;
import com.nas.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/findAll")
    public ResponseEntity<List<Employee>> getAll(){
        LOGGER.info("Get All Employees called");
        return ResponseEntity.ok().body(employeeRepository.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") Long id){
        LOGGER.info("Get Employee by Id: {} called", id);
        return ResponseEntity.ok().body(employeeRepository.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> add(@RequestBody Employee employee){
        LOGGER.info("Employee {} added", employee);
        return ResponseEntity.ok().body(employeeRepository.add(employee));
    }

    @GetMapping("/department/{depId}")
    public ResponseEntity<List<Employee>> getByDepartmentId(@PathVariable("depId") Long id){
        LOGGER.info("Employee with department id: {} returned", id);
        return ResponseEntity.ok().body(employeeRepository.findByDepartment(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
        LOGGER.info("Delete Employee by Id: {} called", id);
        return ResponseEntity.ok().body(employeeRepository.delete(id));
    }
}
