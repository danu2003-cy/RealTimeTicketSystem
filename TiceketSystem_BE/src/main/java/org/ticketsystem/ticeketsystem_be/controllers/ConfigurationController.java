package org.ticketsystem.ticeketsystem_be.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ticketsystem.ticeketsystem_be.Util.StandardResponse;
import org.ticketsystem.ticeketsystem_be.dto.ConfigurationDTO;

@RestController
@CrossOrigin
@RequestMapping("Config")
public class ConfigurationController {

    @GetMapping(path = "/get-config")
    public ResponseEntity<StandardResponse> getConfig() {
        ConfigurationDTO data = null;

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",data),HttpStatus.CREATED
        );
    }

}
