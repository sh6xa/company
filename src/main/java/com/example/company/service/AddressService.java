package com.example.company.service;

import com.example.company.entity.Address;
import com.example.company.payload.ApiResponse;
import com.example.company.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public ApiResponse getOne(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()) return new ApiResponse("Not",false);
        return new ApiResponse("Success!",true,optionalAddress.get());
    }

    public ApiResponse edit(Integer id, Address address) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()) return new ApiResponse("Not",false);
        Address address1 = optionalAddress.get();
       if(!address.getHomeNumber().isEmpty()) address1.setHomeNumber(address.getHomeNumber());
       if(!address.getStreet().isEmpty()) address1.setStreet(address.getStreet());
        addressRepository.save(address1);
        return new ApiResponse("Edited!",true);


    }

    public ApiResponse save(Address address) {
        addressRepository.save(address);
        return new ApiResponse("Success",true);
    }
}
