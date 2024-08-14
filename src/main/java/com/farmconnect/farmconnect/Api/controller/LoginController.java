package com.farmconnect.farmconnect.Api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farmconnect.farmconnect.Api.model.LoginM;
import com.farmconnect.farmconnect.Api.repo.Loginrepo;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private Loginrepo loginrepo;

    @PostMapping("/insertdata")
    public ResponseEntity<Object> insertdata(@RequestBody LoginM data) {
        LoginM savedEmp = loginrepo.save(data);
        return generateResponse("Employee saved successfully", HttpStatus.OK, savedEmp);
    }

    @GetMapping("/getAlldata")
    public ResponseEntity<Object> getAlldata() {
        List<LoginM> userdata = loginrepo.findAll();
        return generateResponse("All employees fetched successfully", HttpStatus.OK, userdata);
    }

    @PostMapping("/updatedata")
    public ResponseEntity<Object> updatedata(@RequestBody LoginM data) {
        Optional<LoginM> optionaluser = loginrepo.findById(data.getId());
        if (optionaluser.isPresent()) {
            LoginM existinguser = optionaluser.get();
            existinguser.setName(data.getName());
            existinguser.setPhone(data.getPhone());
            existinguser.setAddress(data.getAddress());
            existinguser.setPincode(data.getPincode());
            existinguser.setPassword(data.getPassword());
            LoginM updateduser = loginrepo.save(existinguser);
            return generateResponse("Employee updated successfully", HttpStatus.OK, updateduser);
        } else {
            return generateResponse("Employee not found", HttpStatus.NOT_FOUND, null);
        }
    }

    @PostMapping("/deletedata")
    public ResponseEntity<Object> deleteuserById(@RequestBody LoginM data) {
        Optional<LoginM> optionaluser = loginrepo.findById(data.getId());
        if (optionaluser.isPresent()) {
            loginrepo.deleteById(data.getId());
            return generateResponse("Employee deleted successfully", HttpStatus.OK, data);
        } else {
            return generateResponse("Employee not found", HttpStatus.NOT_FOUND, null);
        }
    }


    private ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);
        return new ResponseEntity<>(map, status);
    }
}
