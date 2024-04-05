package com.pruebatecnica.dtos;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase que contiene los datos del request para actualizar el empleadp
 */
@Getter
@Setter
@ToString
public class RequestUpdateEmployeeDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * Id del empleado a actualizar
	 */
	private BigInteger idEmployee;

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
	 * Id del cargo del empleado
	 */
	private BigInteger idPosition;
}