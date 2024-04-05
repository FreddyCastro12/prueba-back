package com.pruebatecnica.services;

import com.pruebatecnica.constants.SQLConstants;
import com.pruebatecnica.dtos.EmployeeDTO;
import com.pruebatecnica.dtos.PositionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que contiene los metodos para la administracion de los cargos de los empleados
 */
@Service
public class PositionService {
    /**
     * Datasource de la conexion a la base de datos
     */
    @Autowired
    private DataSource ds;

    /**
     * Servicio que permite consultar todos los empleados registrados
     *
     * @return Lista de empleados
     */
    public List<PositionDTO> getPositions() throws Exception {
        List<PositionDTO> positions = new ArrayList<>();

        Connection connection = this.ds.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet res = null;

        try {
            preparedStatement = connection.prepareStatement(SQLConstants.GET_POSITIONS);
            res = preparedStatement.executeQuery();

            while (res.next()) {
                PositionDTO position = new PositionDTO();
                position.setIdPosition(BigInteger.valueOf(res.getInt(1)));
                position.setPositionName(res.getString(2));

                positions.add(position);
            }
        } finally {
            res.close();
            preparedStatement.close();
            connection.close();
        }
        return positions;
    }
}
