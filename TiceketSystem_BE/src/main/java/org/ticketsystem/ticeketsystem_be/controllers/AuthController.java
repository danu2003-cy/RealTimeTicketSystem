package org.ticketsystem.ticeketsystem_be.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ticketsystem.ticeketsystem_be.Service.AuthService;
import org.ticketsystem.ticeketsystem_be.dto.CustomerRegistrationDto;
import org.ticketsystem.ticeketsystem_be.dto.LoginDto;
import org.ticketsystem.ticeketsystem_be.dto.VendorRegistrationDto;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup/customer")
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerRegistrationDto dto) {
        try {
            return ResponseEntity.ok(authService.registerCustomer(dto));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/signup/vendor")
    public ResponseEntity<?> registerVendor(@RequestBody VendorRegistrationDto dto) {
        try {
            return ResponseEntity.ok(authService.registerVendor(dto));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        try {
            return ResponseEntity.ok(authService.login(dto));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}

