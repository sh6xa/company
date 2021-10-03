package com.example.company.service;

import com.example.company.entity.Address;
import com.example.company.entity.Department;
import com.example.company.entity.Worker;
import com.example.company.payload.ApiResponse;
import com.example.company.payload.WorkerDto;
import com.example.company.repository.AddressRepository;
import com.example.company.repository.CompanyRepository;
import com.example.company.repository.DepartmentRepository;
import com.example.company.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    WorkerRepository workerRepository;
    public ApiResponse save(WorkerDto workerDto) {
        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (!optionalAddress.isPresent()) return new ApiResponse("Not",false);
        worker.setAddress(optionalAddress.get());
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent()) return new ApiResponse("NOt",false);
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new ApiResponse("Saved!",true);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()) return new ApiResponse("Not",false);
        return new ApiResponse("Success!",true,optionalWorker.get());
    }

    public ApiResponse edit(Integer id, WorkerDto workerDto) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()) return new ApiResponse("Not",false);
        Worker worker = optionalWorker.get();
        if(!workerDto.getName().isEmpty())worker.setName(workerDto.getName());
        if(!workerDto.getPhoneNumber().isEmpty())worker.setPhoneNumber(workerDto.getPhoneNumber());
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (!optionalAddress.isPresent()) return new ApiResponse("Not",false);
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent()) return new ApiResponse("NOt",false);
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new ApiResponse("Edited!",true);

    }

    public ApiResponse delete(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()) return new ApiResponse("Not",false);
        workerRepository.deleteById(id);
        return new ApiResponse("Deleted",true);

    }
}
