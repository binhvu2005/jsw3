package com.example.ss3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {
    private int id;
    private String name;
    private String email;
    private Double salary;
}
