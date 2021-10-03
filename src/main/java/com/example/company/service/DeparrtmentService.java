package com.example.company.service;

import com.example.company.entity.Company;
import com.example.company.entity.Department;
import com.example.company.payload.ApiResponse;
import com.example.company.payload.DepartmentDto;
import com.example.company.repository.CompanyRepository;
import com.example.company.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeparrtmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    public ApiResponse save(DepartmentDto departmentDto){
        Department department =new Department();
        department.setName(departmentDto.getName());
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent()) return new ApiResponse("NOt",false);
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("Saved!",true);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) return new ApiResponse("NOT",false);
        return new ApiResponse("Success!",true,optionalDepartment.get());
    }

    public ApiResponse edit(Integer id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) return new ApiResponse("NOT",false);
        Department department = optionalDepartment.get();
        if(!departmentDto.getName().isEmpty())department.setName(departmentDto.getName());

        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent()) return new ApiResponse("NOt",false);
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("Edited!",true);


    }

    public ApiResponse delete(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) return new ApiResponse("NOT",false);
        departmentRepository.deleteById(id);
        return new ApiResponse("Deleted!",true);

    }
}
