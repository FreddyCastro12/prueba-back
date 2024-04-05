package com.pruebatecnica.dtos;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase que contiene los datos del empleado
 */
@Getter
@Setter
@ToString
public class EmployeeDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * Id del empleado
	 */
	private BigInteger id;

	/**
	 * Cedula del empleado
	 */
	private Integer identification;

	/**
	 * Nombre del empleado
	 */
	private String name;

	/**
	 * Foto de la cara de la persona
	 */
	private String image;

	/**
	 * Fecha de ingresa
	 */
	private Date entryDate;

	/**
	 * Cargo del empleado
	 */
	private PositionDTO position;
}
