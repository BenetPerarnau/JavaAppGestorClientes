package com.jdbc.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Clase Conexión, será la encargada de realizar la conexión con la base de datos.
 * @author Benet
 *
 */

public class Conexion {
	/**
	 * @param Connetion instancia del Objeto Connection
	 */
    private static Connection instance;
    /**
     * Constructor privado de la clase Connection.
     * 
     */
    private Conexion(){} 
    /**
     * Método que genera y devuelve una instancia de la clase Connection.
     * Única forma de generar una conexión con la base de datos.
     * @param db parámetro que indicará a que tipo de base de datos nos conectaremos
     * @return instance
     * @throws ClassNotFoundException
     * @throws SQLException
     * 
     */
    public static Connection getInstance(String db) throws ClassNotFoundException, SQLException{
        if (instance==null){
           if (db.equals("mysql")){
                Class.forName(ConstantesMySQLConnection.driverName);
                instance = DriverManager.getConnection(ConstantesMySQLConnection.URLConnection,
                        ConstantesMySQLConnection.user, 
                        ConstantesMySQLConnection.password);
                System.out.println("Se abre conexion con base de datos");
           } 
       }
     return instance;
     }
    /**
     * Método para cerrar la conexión con la base da datos.
     * @throws SQLException
     */
    public static void cierre() throws SQLException{
        if (instance!=null) {
            instance.close();
            instance=null;
            System.out.println("Conexion con base de datos cerrada");
        }
    }
}
