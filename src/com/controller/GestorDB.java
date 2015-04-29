package com.controller;

import com.datamodel.Cliente;
import com.jdbc.utilities.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase con métodos estáticos que realizaran las operaciones contra la base de datos
 * @author Benet
 *
 */
public class GestorDB {
	
	/**
	 * Método addCliente insertará un nuevo registro "Cliente" en la tabla CLIENTE
	 * @param c parametro de entrada Objeto Cliente a insertar
	 * @return si el valor de retorno es diferente a 0 el insert será contemplado como correcto
	 */
    public static int addCliente(Cliente c){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        int aux=0;
        try{
            connection = Conexion.getInstance("mysql");
            preparedStatement = connection.prepareStatement("INSERT INTO CLIENTE VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, c.getId());
            preparedStatement.setString(2, c.getNombre());
            preparedStatement.setString(3, c.getApellidos());
            preparedStatement.setString(4, c.getDireccionPostal());
            preparedStatement.setString(5, c.getMail());
            preparedStatement.setString(6, c.getTelefon());
            preparedStatement.setInt(7, c.getEdad());
            aux = preparedStatement.executeUpdate();          
        } catch (ClassNotFoundException ex) {
            System.err.println("Error ClassNotFoundException"+ex);
        }catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex){
            System.err.println("Error MySQLIntegrityConstraintViolationException: "+ex);
        }catch (SQLException ex) {
            System.err.println("Error SQLException"+ex);
        }finally{
            try {
                if (preparedStatement!=null) preparedStatement.close();
                //FAIL
                Conexion.cierre();
                //
            } catch (SQLException ex) {
                 System.err.println("Error CIERRE SQLException"+ex);
            }
        }
    return aux;
    }
    /**
     * Método que buca en la Tabla CLIENTE el registro que tiene como campo IDCLIENTE = @value dniValue
     * @param dniValue
     * @return retornará null o la primera coincidéncia en la tabla CLIENTE 
     */
    public static Cliente searchCliente(String dniValue){
        Connection connection=null;
        ResultSet resultset = null;
        Statement statement=null;
        Cliente cliente =  null;
        try {
            connection = Conexion.getInstance("mysql");
            statement = connection.createStatement();
            resultset = statement.executeQuery("SELECT * FROM CLIENTE WHERE IDCLIENTE='"+dniValue+"'");
            if (resultset.first()) { 
                String dni = resultset.getString(1);
                String nombre = resultset.getString(2);
                String apellido =resultset.getString(3);
                String direccionPostal =resultset.getString(4);
                String email =resultset.getString(5);
                String telefono =resultset.getString(6);
                int edad =resultset.getInt(7);
                cliente = new Cliente(dni,nombre,apellido, direccionPostal,email,telefono,edad);
            }
        } catch (ClassNotFoundException ex) {
            System.err.println("Error ClassNotFoundException"+ex);
        } catch (SQLException ex) {
            System.err.println("Error SQLException: "+ex);
        }finally{
            try {
                if (statement!=null) statement.close();
                if (resultset!=null) resultset.close();
                //FAIL
                //
            } catch (SQLException ex) {
                System.err.println("Error CIERRE SQLException"+ex);
            }
        }
    return cliente;
    }
    /**
     * Método que hace un select * en la la tabla CLIENTE de la base de datos.
     * @return retornará un array de Objetos Cliente  en caso de haber uno o más registros en la tabla o el valor null
     */
    public static List<Cliente> getClientes(){
        Connection connection=null;
        Statement statement=null;
        ResultSet resultset = null;
        List<Cliente> lista = null;
        try {
            connection = Conexion.getInstance("mysql");
            statement = connection.createStatement();
            resultset = statement.executeQuery("SELECT * FROM CLIENTE");
            if (resultset.isBeforeFirst()) {    
               //Si hay registros en la tabla
                lista = new ArrayList<Cliente>();
                while (resultset.next()){
                    String dni = resultset.getString(1);
                    String nombre = resultset.getString(2);
                    String apellido =resultset.getString(3);
                    String direccionPostal =resultset.getString(4);
                    String email =resultset.getString(5);
                    String telefono =resultset.getString(6);
                    int edad =resultset.getInt(7);
                    lista.add(new Cliente(dni,nombre,apellido, direccionPostal,email,telefono,edad));
                }
            }
        } catch (ClassNotFoundException ex) {
            System.err.println("Error ClassNotFoundException"+ex);
        } catch (SQLException ex) {
            System.err.println("Error SQLException: "+ex);
        }finally{
            try {
                if (statement!=null) statement.close();
                if (resultset!=null) resultset.close();
                //FAIL
                Conexion.cierre();
                //
            } catch (SQLException ex) {
                System.err.println("Error CIERRE SQLException"+ex);
            }
        }
    return lista;
    }    
}
