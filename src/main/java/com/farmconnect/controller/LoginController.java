package com.farmconnect.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmconnect.model.Login;
import com.farmconnect.repo.Loginrepo;

@RestController
@RequestMapping("/api/")
public class LoginController { // Renamed to avoid conflict with the model class

    @Autowired
    private Loginrepo loginrepo;

    @PostMapping("/saveEmployee")
    public ResponseEntity<Object> saveEmployee(@RequestBody Login emp) {
        Login savedEmp = loginrepo.save(emp);
        return generateResponse("Employee saved successfully", HttpStatus.OK, savedEmp);
    }

    @GetMapping("/getAllEmployees")
    public Iterable<Login> getAllEmployees() {
        return loginrepo.findAll();
    }

    @PostMapping("/updateEmployee")
    public ResponseEntity<Object> updateEmployee(@RequestBody Login emp) {
        Optional<Login> optionalEmp = loginrepo.findById(emp.getId());
        if (optionalEmp.isPresent()) {
            Login existingEmp = optionalEmp.get();
            existingEmp.setName(emp.getName());
            existingEmp.setPhone(emp.getPhone());
            existingEmp.setAddress(emp.getAddress());
            existingEmp.setPincode(emp.getPincode());
            existingEmp.setPassword(emp.getPassword());
            Login updatedEmp = loginrepo.save(existingEmp);
            return generateResponse("Employee updated successfully", HttpStatus.OK, updatedEmp);
        } else {
            return generateResponse("Employee not found", HttpStatus.NOT_FOUND, null);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> deleteDataById(@RequestBody Login emp) {
        Optional<Login> optionalEmp = loginrepo.findById(emp.getId());
        if (optionalEmp.isPresent()) {
            loginrepo.deleteById(emp.getId());
            return generateResponse("Employee deleted successfully", HttpStatus.OK, emp);
        } else {
            return generateResponse("Employee not found", HttpStatus.NOT_FOUND, emp);
        }
    }

    private ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message); // Corrected typo
        map.put("status", status.value());
        map.put("data", responseObj);
        return new ResponseEntity<>(map, status);
    }
}
