package com.example.company.controller;

import com.example.company.entity.Company;
import com.example.company.entity.Department;
import com.example.company.payload.ApiResponse;
import com.example.company.payload.DepartmentDto;
import com.example.company.repository.CompanyRepository;
import com.example.company.repository.DepartmentRepository;
import com.example.company.service.DeparrtmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/api/Department")
@RestController
public class DepartmentController {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DeparrtmentService deparrtmentService;
   public HttpEntity<ApiResponse> save(DepartmentDto departmentDto){
       ApiResponse apiResponse = deparrtmentService.save(departmentDto);
       return ResponseEntity.status(apiResponse.isSuccess()
                       ? HttpStatus.CREATED :
                       HttpStatus.CONFLICT).
               body(apiResponse);
   }

   @GetMapping
    public HttpEntity<List<Department>> getAll(){
       return ResponseEntity.ok(departmentRepository.findAll());
   }
   @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getOne(@PathVariable Integer id){
       return ResponseEntity.ok(deparrtmentService.getOne(id));
   }
   @PutMapping
    public HttpEntity<ApiResponse> edit(@PathVariable Integer id,@RequestBody DepartmentDto departmentDto){
       ApiResponse response = deparrtmentService.edit(id, departmentDto);
       return ResponseEntity.status(response.isSuccess()
               ? HttpStatus.ACCEPTED
               : HttpStatus.CONFLICT).body(response);

   }
   @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id){
       return ResponseEntity.ok(deparrtmentService.delete(id));
   }



}
