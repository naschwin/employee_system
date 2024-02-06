package com.nas.departmentservice.controller;

import com.nas.departmentservice.client.EmployeeClient;
import com.nas.departmentservice.model.Department;
import com.nas.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeClient employeeClient;

    @GetMapping("/findAll")
    public ResponseEntity<List<Department>> getAll(){
        LOGGER.info("Get All Departments called");
        return ResponseEntity.ok().body(departmentRepository.getAllDepartments());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Department> getById(@PathVariable("id") Long id){
        LOGGER.info("Get Department by Id: {} called", id);
        return ResponseEntity.ok().body(departmentRepository.findById(id));
    }

    @GetMapping("/findAllWithEmployees")
    public ResponseEntity<List<Department>> findAllWithEmployees(){
        LOGGER.info("Get All Departments with employees called");
        List<Department> deps = departmentRepository.getAllDepartments();
        deps.forEach(department -> department.setEmployees(employeeClient.findByDepartment(department.getId())));
        return ResponseEntity.ok().body(deps);
    }

    @PostMapping("/add")
    public ResponseEntity<Department> add(@RequestBody Department department){
        LOGGER.info("Department {} added", department);
        return ResponseEntity.ok().body(departmentRepository.addDepartment(department));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
        LOGGER.info("Delete Department by Id: {} called", id);
        departmentRepository.deleteDepartment(id);
        return ResponseEntity.ok().body("Deleted Successfully");
    }




}
