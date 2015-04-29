package com.datamodel;
/**
 * @author Benet
 * Clase con el modelo Cliente que se encuentra en la base de datos
 */
public class Cliente {  
	/**
	 * @param id Variable que dispone del valor del identificador único de cada Cliente
	 */
    private String id;
    /**
     * @param nombre Variable que dipone de la cadena de texto formada por el nombre del Cliente
     */
    private String nombre;
    /**
     * @param apellidos Variable que dipone de la cadena de texto formada por el apellido del Cliente
     */
    private String apellidos;
    /**
     * @param direccionPostal Variable que dipone de la cadena de texto con el valor del Código Postal de la Población del Cliente
     */
    private String direccionPostal;
    /**
     * @param mail Variable que dipone de la cadena de texto con el e-mail del Cliente
     */
    private String mail;
    /**
     * @param telefon Variable que dipone de la cadena de texto formada por los digitos del teléfono del Cliente
     */
    private String telefon;
    /**
     * @param edad Variable que dipone un entero con el valor de la edad del Cliente
     */
    private int edad;
    /**
     * Constructor de la clase recibe todos los atributos al crear un nuevo Cliente
     * @param id
     * @param nombre
     * @param apellidos
     * @param direccionPostal
     * @param mail
     * @param telefon
     * @param edad
     */
    public Cliente(String id, String nombre, String apellidos, 
            String direccionPostal, String mail, String telefon, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos=apellidos;
        this.direccionPostal = direccionPostal;
        this.mail = mail;
        this.telefon = telefon;
        this.edad = edad;
    }
    /**
     * GETERS de los atributos de la clase
     */
    public String getId() {return id;}
    public String getNombre() {return nombre;}
    public String getApellidos() {return apellidos;}
    public String getDireccionPostal() {return direccionPostal;}
    public String getMail() {return mail;}
    public String getTelefon() {return telefon;}
    public int getEdad() {return edad;}
}
