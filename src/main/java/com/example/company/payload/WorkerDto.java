package com.example.company.payload;

import lombok.Data;

@Data
public class WorkerDto {
    private String name,phoneNumber;
    private Integer addressId,departmentId;
}
