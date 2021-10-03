package com.example.company.service;

import com.example.company.entity.Address;
import com.example.company.entity.Company;
import com.example.company.payload.ApiResponse;
import com.example.company.payload.CompanyDto;
import com.example.company.repository.AddressRepository;
import com.example.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CompanyRepository companyRepository;
    public ApiResponse save(CompanyDto companyDto) {
        Company company =new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent()) new ApiResponse("Not",false);
        company.setAddress(optionalAddress.get());
        companyRepository.save(company);
        return new ApiResponse("Saved!",true);

    }

    public ApiResponse getOne(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()) return new ApiResponse("Not",false);
        return new ApiResponse("Success!",true,optionalCompany.get());
    }

    public ApiResponse edit(Integer id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()) return new ApiResponse("Not",false);
        Company company = optionalCompany.get();
         if(!companyDto.getCorpName().isEmpty())company.setCorpName(companyDto.getCorpName());
         if(!companyDto.getDirectorName().isEmpty())company.setDirectorName(companyDto.getDirectorName());
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent()) new ApiResponse("Not",false);
         company.setAddress(optionalAddress.get());
         companyRepository.save(company);
         return new ApiResponse("Edited",true);

    }

    public ApiResponse delete(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()) return new ApiResponse("Not",false);
        companyRepository.deleteById(id);
        return new ApiResponse("Deleted!",true);

    }
}
