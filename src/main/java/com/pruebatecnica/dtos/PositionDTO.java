package com.pruebatecnica.dtos;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase que contiene los datos del cargo del empleado
 */
@Getter
@Setter
@ToString
public class PositionDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * Id del cargo
	 */
	private BigInteger idPosition;

	/**
	 * Nombre del cargo
	 */
	private String positionName;
}
