package com.example.company.controller;

import com.example.company.entity.Address;
import com.example.company.entity.Worker;
import com.example.company.payload.ApiResponse;
import com.example.company.payload.WorkerDto;
import com.example.company.repository.WorkerRepository;
import com.example.company.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @Autowired
    WorkerRepository workerRepository;

    @PostMapping
    public HttpEntity <ApiResponse> save(@RequestBody WorkerDto workerDto) {
        ApiResponse apiResponse = workerService.save(workerDto);
        return ResponseEntity.status(apiResponse.isSuccess()
                        ? HttpStatus.CREATED :
                        HttpStatus.CONFLICT).
                body(apiResponse);

    }

    @GetMapping
    public HttpEntity<List<Worker>> getAll() {
        return ResponseEntity.ok(workerRepository.findAll());

    }

    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getOne(@PathVariable Integer id) {
        return  ResponseEntity.ok(workerService.getOne(id));
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@PathVariable Integer id, @RequestBody WorkerDto workerDto) {
        ApiResponse response = workerService.edit(id, workerDto);
        return ResponseEntity.status(response.isSuccess()
                ? HttpStatus.ACCEPTED
                : HttpStatus.CONFLICT).body(response);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id){
        return ResponseEntity.ok(workerService.delete(id));

    }
}
