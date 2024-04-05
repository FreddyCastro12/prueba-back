package com.pruebatecnica.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * Clase que contiene los datos de la respuesta de conbvertir imagen a String
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Mensjae de respuesta
     */
    private String mensaje;
}
