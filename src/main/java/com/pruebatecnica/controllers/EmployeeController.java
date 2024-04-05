package com.pruebatecnica.controllers;

import java.math.BigInteger;

import com.pruebatecnica.dtos.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pruebatecnica.dtos.RequestCreateEmployeeDTO;
import com.pruebatecnica.dtos.RequestUpdateEmployeeDTO;
import com.pruebatecnica.services.EmployeeService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	/**
	 * Servicio que contiene las funciones para empleados
	 */
	@Autowired
	private EmployeeService service;

	@PostMapping("/createEmployee")
	public ResponseEntity<Object>  createEmployee(@RequestBody RequestCreateEmployeeDTO request) {
		try {
			this.service.createEmployee(request);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK.getReasonPhrase()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("createEmployee: " + e.getMessage());
		}
	}

	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<Object> getEmployee(@PathVariable("id") BigInteger idEmployee) {
		try {
			return ResponseEntity.ok().body(this.service.getEmployee(idEmployee));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("getEmployee: " + e.getMessage());
		}
	}

	@GetMapping("/getEmployees")
	public ResponseEntity<Object> getEmployees() {
		try {
			return ResponseEntity.ok().body(this.service.getEmployees());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("getEmployees: " + e.getMessage());
		}
	}

	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Object>  deleteEmployee(@PathVariable("id") BigInteger idEmployee) {
		try {
			this.service.deleteEmployee(idEmployee);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK.getReasonPhrase()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("deleteEmployee: " + e.getMessage());
		}
	}

	@PutMapping("/updateEmployee")
	public ResponseEntity<Object>  updateEmployee(@RequestBody RequestUpdateEmployeeDTO request) {
		try {
			this.service.updateEmployee(request);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK.getReasonPhrase()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("updateEmployee: " + e.getMessage());
		}
	}

	@PostMapping("/uploadImage")
	public ResponseEntity<Object> uploadImage(@RequestParam("file") MultipartFile multiPart) {
		try {
			return ResponseEntity.ok().body(this.service.uploadImage(multiPart.getBytes()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("uploadImage: " + e.getMessage());
		}
	}
}
