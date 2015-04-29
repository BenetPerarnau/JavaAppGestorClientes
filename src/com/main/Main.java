package com.main;

import com.controller.GestorDB;
import com.datamodel.Cliente;
import com.jdbc.utilities.Conexion;
import java.sql.*;
import java.util.List;
import javax.swing.JOptionPane;


/**
 * 
 * @author Marta
 */

public class Main {

	/**
	 * 
	 */
    static Object[] opcionesMenu ={"1. Registrar Cliente", "2. Consultar Cliente", "3. Ver Lista Clientes", "4. Salir"};
    /**
     * Punto de entrada a la aplicación
     * @param args
     */
    public static void main(String[] args) {
        showMenu();
    }
    /**
     * Método privado que muestra un Dialog de selección.
     */
    private static void showMenu(){
        Object seleccion = JOptionPane.showInputDialog(
                null,"Selecciona opcion","Selector de opciones",
                JOptionPane.QUESTION_MESSAGE,null,
                opcionesMenu, opcionesMenu[0]);
        if (seleccion!=null){
                defineSeleccion(seleccion.toString());
        }else{System.exit(0);}
    }
    /**
     * Método auxiliar que se encarga de distribuir las acciones que realizará la aplicación
     * dependiendo del parametro de entrada @value seleccion que viene dado por el método
     * anterior showMenu()
     * @param seleccion parametro de entrada al método que define la acción que se realizará
     */
    private static void defineSeleccion(String seleccion) {
       
        if (seleccion.equals(opcionesMenu[0])){
            registrarCliente();
            showMenu();
        }else if (seleccion.equals(opcionesMenu[1])){
            String dni=JOptionPane.showInputDialog("Indica el DNI del cliente");
            buscarCliente(dni);
            showMenu();
        }else if (seleccion.equals(opcionesMenu[2])){
            obtenerRegistros();
            showMenu();
        }else{
            System.exit(0);
        }
 }
  /**
   * Método que se encarga de gestionar la recepción de las variables necesarias para insertar
   * un nuevo Cliente en la tabla CLIENTE
   */
 private static void registrarCliente() {
   
    String datosSolicitados[] = {"DNI : ","Nombre :", "Apellidos: ","Direccion postal: ","Email: ", "Telefono: ","Edad: "};
    String datosPersona[] = new String[datosSolicitados.length];
    boolean datoOK=false;
    boolean registro=true;
    
    for (int i = 0; i < datosSolicitados.length; i++) {
        datoOK=false;

        while (!datoOK){
            datosPersona[i]=JOptionPane.showInputDialog(null, "Introduzca\n\n"+
                datosSolicitados[i],"Datos Cliente",JOptionPane.INFORMATION_MESSAGE);
            if (datosPersona[i]==null){
                  JOptionPane.showMessageDialog(null,"Cancelada la introducción de datos, ","CANCEL",JOptionPane.ERROR_MESSAGE);
                  registro=false;
                  break;
            }else{datoOK=!(datosPersona[i].equals(""));}
        }
        if (registro==false) break;
        System.out.println(datosSolicitados[i]+datosPersona[i]);

    }
    if (registro){
        Cliente c = new Cliente(datosPersona[0], datosPersona[1], datosPersona[2], datosPersona[3], 
            datosPersona[4], datosPersona[5], Integer.parseInt(datosPersona[6]));

            int fila = GestorDB.addCliente(c);
            if (fila==0){
                JOptionPane.showMessageDialog(null,"Error en la inserción de datos en la base de datos, ","ERROR",JOptionPane.ERROR_MESSAGE);
            }


    }
 }
 
/**
 * Método que será el encargado de llamar a la función del selct * de la calse GestorDB
 * y printar por consola los registros encontrados.
 */
 private static void obtenerRegistros() {

  List< Cliente> lista = GestorDB.getClientes();
  
  if (lista!=null) {
    int numeroCliente=0;

    for (Cliente c : lista) {
        numeroCliente++;
        System.out.println("****************Persona "+numeroCliente+"**********************");
        System.out.println("Id Cliente: "+c.getId());
        System.out.println("Nombre Cliente: "+c.getNombre());
        System.out.println("Apellidos Cliente: "+c.getApellidos());
        System.out.println("Dirección postal Cliente: "+c.getDireccionPostal());
        System.out.println("Email Cliente: "+c.getMail());
        System.out.println("Telefono Cliente: "+c.getTelefon());
        System.out.println("*************************************************\n");
    }
    }else{
        JOptionPane.showMessageDialog(null,"Actualmente no " +
     "existen registros de personas","INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
    }
   
 }
 /**
  * Método encargado de llamar a la funcion searchCliente de la clase GestorDB
  * y imprimir por pantalla los atrtibutos del cliente obtenido
  * @param id parametro de coincidéncia para hacer una select en la tabla
  */
 private static void buscarCliente(String id) {
 Cliente c = GestorDB.searchCliente(id);
  if (c!=null) {

    System.out.println("****************Cliente*************************");
    System.out.println("Id Cliente: "+c.getId());
    System.out.println("Nombre Cliente: "+c.getNombre());
    System.out.println("Apellidos Cliente: "+c.getApellidos());
    System.out.println("Dirección postal Cliente: "+c.getDireccionPostal());
    System.out.println("Email Cliente: "+c.getMail());
    System.out.println("Telefono Cliente: "+c.getTelefon());
    System.out.println("*************************************************\n");

  }else{
   JOptionPane.showMessageDialog(null,"El dni facilitado " +
     "no corresponde a ningún cliente","INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
  }
 
 }
    
}
