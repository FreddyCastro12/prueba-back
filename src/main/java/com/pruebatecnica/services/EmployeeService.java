package com.pruebatecnica.services;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.pruebatecnica.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica.constants.EmployeeConstants;
import com.pruebatecnica.constants.SQLConstants;
import org.springframework.util.Base64Utils;

/**
 * Servicio que contiene los metodos para la administracion de empleados
 */
@Service
public class EmployeeService {
	/**
	 * Datasource de la conexion a la base de datos
	 */
	@Autowired
	private DataSource ds;

	/**
	 * Servicio que permite la creación de empleados
	 *
	 * @return Empleado creado
	 */
	public void createEmployee(RequestCreateEmployeeDTO request) throws Exception {
		if (request.getIdPosition() == null || (request.getName().isEmpty() || request.getName() == null)
				|| (request.getImage() == null || request.getImage().isEmpty())
				|| (request.getIdentification() == null || request.getIdentification() < 0)) {
			throw new Exception(EmployeeConstants.WRONG_DATA);
		}

		String rutaImagen = request.getImage();

		Connection connection = this.ds.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(SQLConstants.INSERT_EMPLOYEE);
			preparedStatement.setInt(1, request.getIdentification());
			preparedStatement.setString(2, request.getName());
			preparedStatement.setString(3, rutaImagen);
			preparedStatement.setInt(4, request.getIdPosition().intValue());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
			connection.close();
		}
	}

	/**
	 * Servicio que permite consultar un empleado por id
	 *
	 * @return Empleado consultado
	 */
	public EmployeeDTO getEmployee(BigInteger idEmployee) throws Exception {
		EmployeeDTO employee;

		Connection connection = this.ds.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet res = null;

		try {
			preparedStatement = connection.prepareStatement(SQLConstants.GET_EMPLOYEE);
			preparedStatement.setInt(1, idEmployee.intValue());

			res = preparedStatement.executeQuery();

			if (res.next()) {
				employee = new EmployeeDTO();
				employee.setId(BigInteger.valueOf(res.getInt(1)));
				employee.setIdentification(res.getInt(2));
				employee.setName(res.getString(3));
				employee.setImage(res.getString(4));
				employee.setEntryDate(res.getDate(5));

				PositionDTO position = new PositionDTO();
				position.setIdPosition(BigInteger.valueOf(res.getInt(6)));
				position.setPositionName(res.getString(7));

				employee.setPosition(position);
			} else {
				throw new Exception(EmployeeConstants.EMPLOYEE_NOT_EXIST);
			}
		} finally {
			res.close();
			preparedStatement.close();
			connection.close();
		}
		return employee;
	}

	/**
	 * Servicio que permite consultar todos los empleados registrados
	 *
	 * @return Lista de empleados
	 */
	public List<EmployeeDTO> getEmployees() throws Exception {
		List<EmployeeDTO> employees = new ArrayList<>();

		Connection connection = this.ds.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet res = null;

		try {
			preparedStatement = connection.prepareStatement(SQLConstants.GET_EMPLOYEES);
			res = preparedStatement.executeQuery();

			while (res.next()) {
				EmployeeDTO employee = new EmployeeDTO();
				employee.setId(BigInteger.valueOf(res.getInt(1)));
				employee.setIdentification(res.getInt(2));
				employee.setName(res.getString(3));
				employee.setImage(res.getString(4));
				employee.setEntryDate(res.getDate(5));

				PositionDTO position = new PositionDTO();
				position.setIdPosition(BigInteger.valueOf(res.getInt(6)));
				position.setPositionName(res.getString(7));

				employee.setPosition(position);

				employees.add(employee);
			}
		} finally {
			res.close();
			preparedStatement.close();
			connection.close();
		}
		return employees;
	}

	/**
	 * Servicio que permite eliminar un empleado
	 */
	public void deleteEmployee(BigInteger idEmployee) throws Exception {
		Connection connection = this.ds.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(SQLConstants.DELETE_EMPLOYEE);
			preparedStatement.setInt(1, idEmployee.intValue());

			preparedStatement.executeUpdate();
		} finally {
			preparedStatement.close();
			connection.close();
		}
	}

	/**
	 * Servicio que permite actualizar un empleado
	 *
	 * @return Empleado actualizado
	 */
	public void updateEmployee(RequestUpdateEmployeeDTO request) throws Exception {
		if (request.getIdEmployee() != null && ((request.getName() != null && !request.getName().isEmpty())
				|| request.getIdentification() != null
				|| (request.getImage() != null && !request.getImage().isEmpty())
				|| request.getIdPosition() != null)
		) {
			StringBuilder sql = new StringBuilder(SQLConstants.UPDATE_EMPLOYEE);

			if (request.getIdentification() != null) {
				sql.append(SQLConstants.UPDATE_EMPLOYEE_IDENTIFICATION + request.getIdentification()
						+ EmployeeConstants.COMMA);
			}

			if (request.getName() != null && !request.getName().isEmpty()) {
				sql.append(SQLConstants.UPDATE_EMPLOYEE_NAME + request.getName() + EmployeeConstants.QUOTE_COMMA);
			}

			if (request.getImage() != null && request.getImage().isEmpty()) {
				sql.append(SQLConstants.UPDATE_EMPLOYEE_IMAGE + request.getImage() + EmployeeConstants.QUOTE_COMMA);
			}

			if (request.getIdPosition() != null) {
				sql.append(SQLConstants.UPDATE_EMPLOYEE_POSITION + request.getIdPosition() + EmployeeConstants.COMMA);
			}

			sql.deleteCharAt(sql.length() - 1);
			sql.append(SQLConstants.UPDATE_EMPLOYEE_WHERE + request.getIdEmployee());

			Connection connection = this.ds.getConnection();
			PreparedStatement preparedStatement = null;

			try {
				preparedStatement = connection.prepareStatement(sql.toString());
				preparedStatement.executeUpdate();
			} finally {
				preparedStatement.close();
				connection.close();
			}
		} else {
			throw new Exception(EmployeeConstants.WRONG_DATA);
		}
	}

	/**
	 * Servicio para capturar la imagen subida, procesrala y devolver el array de bytes convertido a String
	 */
	public ResponseUploadImageDTO uploadImage (byte[] image) {
		ResponseUploadImageDTO response = new ResponseUploadImageDTO();

		response.setImage(Base64Utils.encodeToString(image));

		return response;
	}
}
