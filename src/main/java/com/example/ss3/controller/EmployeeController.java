package com.example.ss3.controller;

import com.example.ss3.entity.Employee;
import com.example.ss3.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping
    public String listEmployees(Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam(defaultValue = "id") String sortBy,
                                @RequestParam(required = false) String phoneNumber) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Employee> employees;

        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            employees = employeeRepository.findByPhoneNumberContaining(phoneNumber, pageable);
        } else {
            employees = employeeRepository.findAll(pageable);
        }

        model.addAttribute("employees", employees);
        return "employee/list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/form";
    }

    // Lưu khi thêm/sửa
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "employee/form";
        } else {
            return "redirect:/employees";
        }
    }

    // Xóa nhân viên
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        }
        return "redirect:/employees";
    }
}
