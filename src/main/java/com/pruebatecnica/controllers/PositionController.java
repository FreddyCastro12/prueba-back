package com.pruebatecnica.controllers;

import com.pruebatecnica.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/positions")
public class PositionController {
    /**
     * Servicio que contiene las funciones para empleados
     */
    @Autowired
    private PositionService service;

    @GetMapping("/getPositions")
    public ResponseEntity<Object> getPositions() {
        try {
            return ResponseEntity.ok().body(this.service.getPositions());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("getPositions: " + e.getMessage());
        }
    }
}
