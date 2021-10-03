package com.example.company.controller;

import com.example.company.entity.Address;
import com.example.company.payload.ApiResponse;
import com.example.company.repository.AddressRepository;
import com.example.company.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    AddressRepository addressRepository;
    @PostMapping
    public HttpEntity<ApiResponse> save(@RequestBody Address address){
        ApiResponse save = addressService.save(address);
        return ResponseEntity.status(save.isSuccess()
                        ? HttpStatus.CREATED :
                        HttpStatus.CONFLICT).
                body(save);


    }

    @GetMapping
    public HttpEntity< List<Address>> getAll(){
        return ResponseEntity.ok(addressRepository.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<ApiResponse>  getOne(@PathVariable Integer id){
        return ResponseEntity.ok(addressService.getOne(id));
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse>edit(@PathVariable Integer id,@RequestBody Address address){
        ApiResponse apiResponse = addressService.edit(id, address);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED
                : HttpStatus.CONFLICT).body(apiResponse);
    }

}
