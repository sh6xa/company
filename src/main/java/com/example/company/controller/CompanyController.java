package com.example.company.controller;

import com.example.company.entity.Company;
import com.example.company.payload.ApiResponse;
import com.example.company.payload.CompanyDto;
import com.example.company.repository.CompanyRepository;
import com.example.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyRepository companyRepository;

    @PostMapping
    public HttpEntity<ApiResponse> save(@RequestBody CompanyDto companyDto) {
        ApiResponse apiResponse = companyService.save(companyDto);
        return ResponseEntity.status(apiResponse.isSuccess()
                        ? HttpStatus.CREATED :
                        HttpStatus.CONFLICT).
                body(apiResponse);

    }

    @GetMapping
    public HttpEntity<List<Company>> getAll() {
        List<Company> all = companyRepository.findAll();
        return ResponseEntity.ok(all);

    }
    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(companyService.getOne(id));
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@PathVariable Integer id,@RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.edit(id, companyDto);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED
                : HttpStatus.CONFLICT).body(apiResponse);

    }

    @DeleteMapping("/id")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id){
        return ResponseEntity.ok( companyService.delete(id));
    }

}
