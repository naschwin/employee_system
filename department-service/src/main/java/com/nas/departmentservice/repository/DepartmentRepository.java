package com.nas.departmentservice.repository;

import com.nas.departmentservice.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepository {

    private List<Department> departmentList = new ArrayList<>();

    public Department addDepartment(Department department){
        departmentList.add(department);
        return department;
    }

    public List<Department> getAllDepartments(){
        return departmentList;
    }

    public Department findById(Long id){
        return departmentList.stream()
                .filter(
                    department -> department.getId() == id
                ).findFirst().orElseThrow();
    }

    public void deleteDepartment(Long id){
        departmentList = departmentList.stream().filter(department1 -> department1.getId() != id).toList();
    }

}
