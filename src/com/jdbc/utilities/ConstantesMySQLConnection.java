package com.jdbc.utilities;

/**
 * Clase con las variables constantes en la aplicación
 * @author Benet
 *
 */
public class ConstantesMySQLConnection {
	/**
	 * @param driverName Dirección del driver para conectar con la base de datos.
	 */
    public static final String driverName="com.mysql.jdbc.Driver";
    /**
     * @param URLConnection url que apuntará a la conexión con la base de datos.
     */
    public static final String URLConnection="jdbc:mysql://localhost:3306/mysqldemo";
    /**
     * @param user nombre del usuario que se conecta en la base de datos.
     */
    public static final String user="root";
    /**
     * @param password password del usuario @value user para poder acceder en la base de datos.
     */
    public static final String password="root";
}
