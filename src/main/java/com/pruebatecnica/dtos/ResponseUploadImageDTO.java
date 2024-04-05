package com.pruebatecnica.dtos;

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
public class ResponseUploadImageDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Cadena de texto de la imagen
     */
    private String image;
}
