package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que representa un profesor de un instituto
 *
 * @author Juan Antonio Manzano Plaza
 * @version 5
 *
 */
@SuppressWarnings("serial")
public class Profesor implements Serializable {

	private static final String ER_TELEFONO = "^[69][0-9]{8}$";
	private static final String ER_CORREO = "^[a-z0-9]+(\\.[a-z0-9]+)*@[a-z]+(\\.[a-z]+)*(\\.[a-z]{1,4})$";
	private String nombre;
	private String correo;
	private String telefono;

	/**
	 * Constructor de la clase
	 *
	 * @param nombre
	 *            el nombre del profesor
	 * @param correo
	 *            el correo electrónico del profesor
	 * @throws IllegalArgumentException
	 *             si alguno de los parámetros no es válido
	 */
	public Profesor(String nombre, String correo) throws IllegalArgumentException {
		setNombre(nombre);
		setCorreo(correo);
		setTelefono(null);
	}

	/**
	 * Constructor de la clase
	 *
	 * @param nombre
	 *            el nombre del profesor
	 * @param correo
	 *            el correo electrónico del profesor
	 * @param telefono
	 *            el teléfono del profesor
	 * @throws IllegalArgumentException
	 *             si alguno de los parámetros no es válido
	 */
	public Profesor(String nombre, String correo, String telefono) throws IllegalArgumentException {
		setNombre(nombre);
		setCorreo(correo);
		setTelefono(telefono);
	}

	/**
	 * Constructor copia
	 *
	 * @param otro
	 *            el profesor a copiar
	 * @throws IllegalArgumentException
	 *             si el profesor a copiar es nulo
	 */
	public Profesor(Profesor otro) throws IllegalArgumentException {
		if (otro == null)
			throw new IllegalArgumentException("No se puede copiar un profesor nulo.");
		setNombre(otro.getNombre());
		setCorreo(otro.getCorreo());
		setTelefono(otro.getTelefono());

	}

	/**
	 * Método set para el nombre del profesor
	 *
	 * @param nombre
	 *            el nombre del profesor
	 * @throws IllegalArgumentException
	 *             si el nombre es nulo o vacío
	 */
	private void setNombre(String nombre) throws IllegalArgumentException {
		if (nombre == null)
			throw new IllegalArgumentException("El nombre del profesor no puede ser nulo.");
		if (nombre.equals(""))
			throw new IllegalArgumentException("El nombre del profesor no puede estar vacío.");
		this.nombre = nombre;

	}

	/**
	 * Método set para el correo electrónico del profesor
	 *
	 * @param correo
	 *            el correo del profesor
	 * @throws IllegalArgumentException
	 *             si el correo es nulo o no es un correo con formato válido
	 */
	public void setCorreo(String correo) throws IllegalArgumentException {
		if (correo == null)
			throw new IllegalArgumentException("El correo del profesor no puede ser nulo.");
		Matcher m = Pattern.compile(ER_CORREO).matcher(correo);
		if (m.matches())
			this.correo = correo;
		else
			throw new IllegalArgumentException("El correo del profesor no es válido.");

	}

	/**
	 * Método set para el teléfono del profesor
	 *
	 * @param telefono
	 *            el telefono del profesor
	 * @throws IllegalArgumentException
	 *             si el teléfono no tiene un formato válido
	 */
	public void setTelefono(String telefono) throws IllegalArgumentException {
		if (telefono == null) {
			this.telefono = null;
		} else {
			Matcher m = Pattern.compile(ER_TELEFONO).matcher(telefono);
			if (m.matches())
				this.telefono = telefono;
			else
				throw new IllegalArgumentException("El teléfono del profesor no es válido.");
		}
	}

	/**
	 * Método get para el nombre del profesor
	 *
	 * @return el nombre del profesor
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * M�todo get para el correo del profesor
	 *
	 * @return el correo del profesor
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Método get para el teléfono del profesor
	 *
	 * @return el teléfono del profesor
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Método hashCode de la clase. Sirve para diferenciar objetos
	 *
	 * @return el código hash del objeto
	 */
	public int hashCode() {
		return Objects.hash(nombre, correo, telefono);
	}

	/**
	 * Método equals de la clase
	 *
	 * @return True si son iguales, False si no
	 */
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Profesor))
			return false;
		Profesor otro = (Profesor) o;
		if (this.getNombre().equals(otro.getNombre()))
			return true;
		return false;

	}

	/**
	 * Representa un profesor como una cadena de caracteres
	 *
	 * @return la representación del profesor
	 */
	public String toString() {
		String devolver = "[nombre=" + getNombre() + ", correo=" + getCorreo();
		if (getTelefono() == null)
			devolver += "]";
		else
			devolver += ", telefono=" + getTelefono() + "]";
		return devolver;

	}
}
