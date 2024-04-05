package com.pruebatecnica.constants;

/**
 * Clase que permite almacenar todos los SQLs que serán ejecutados
 */
public class SQLConstants {
	/**
	 * SQL que permite insertar un empleado a la base de datos
	 */
	public static final String INSERT_EMPLOYEE = "INSERT INTO employees.employees " +
			"(identification, name, image, id_position) " +
			"VALUES(?, ?, ?, ?)";

	/**
	 * SQL para consultar un empleado por id
	 */
	public static final String GET_EMPLOYEE = "SELECT e.id, e.identification, "
			+ "e.name, e.image, e.entry_date, p.id_position, p.name "
			+ "FROM employees.employees e "
			+ "JOIN employees.positions p ON p.id_position = e.id_position "
			+ "where e.id = ?";

	/**
	 * SQL para consultar todos los empleados
	 */
	public static final String GET_EMPLOYEES = "SELECT e.id, e.identification, "
			+ "e.name, e.image, e.entry_date, p.id_position, p.name " + "FROM employees.employees e "
			+ "JOIN employees.positions p ON p.id_position = e.id_position";

	/**
	 * SQL para eliminar un empleado por id
	 */
	public static final String DELETE_EMPLOYEE = "DELETE FROM employees.employees "
			+ "WHERE id = ?";

	/**
	 * SQLs para actualizar los datos de un empleado
	 */
	public static final String UPDATE_EMPLOYEE = "UPDATE employees.employees SET ";
	public static final String UPDATE_EMPLOYEE_IDENTIFICATION = "identification = ";
	public static final String UPDATE_EMPLOYEE_NAME = "name = '";
	public static final String UPDATE_EMPLOYEE_IMAGE = "image = '";
	public static final String UPDATE_EMPLOYEE_POSITION = "id_position = ";
	public static final String UPDATE_EMPLOYEE_WHERE = " WHERE id = ";

	/**
	 * SQL para consultar la lista de cargos
	 */
	public static final String GET_POSITIONS = "SELECT p.id_position, p.name "
			+ "FROM employees.positions p";
}
