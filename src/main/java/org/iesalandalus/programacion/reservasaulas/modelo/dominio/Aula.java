package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa un aula de un instituto
 *
 * @author Juan Antonio Manzano Plaza
 * @version 3
 *
 */
public class Aula implements Serializable {

	private static final float PUNTOS_POR_PUESTO = 0.5f;
	private static final int MIN_PUESTOS = 10;
	private static final int MAX_PUESTOS = 100;
	private String nombre;
	private int puestos;

	/**
	 * Constructor de la clase
	 *
	 * @param nombre
	 *            el nombre del aula
	 * @param puestos
	 *            el n�mero de plazas que tiene el aula
	 * @throws IllegalArgumentException
	 *             si alguno de los par�metros no es v�lido
	 */
	public Aula(String nombre, int puestos) throws IllegalArgumentException {
		setNombre(nombre);
		setPuestos(puestos);
	}

	/**
	 * Constructor copia
	 *
	 * @param otra
	 *            el aula a copiar
	 * @throws IllegalArgumentException
	 *             si el aula es nula
	 */
	public Aula(Aula otra) throws IllegalArgumentException {
		if (otra == null)
			throw new IllegalArgumentException("No se puede copiar un aula nula.");
		setNombre(otra.getNombre());
		setPuestos(otra.getPuestos());
	}

	/**
	 * M�todo set para el nombre del aula
	 *
	 * @param nombre
	 *            el nombre del aula
	 * @throws IllegalArgumentException
	 *             si el nombre es nulo o vac�o
	 */
	private void setNombre(String nombre) throws IllegalArgumentException {
		if (nombre == null)
			throw new IllegalArgumentException("El nombre del aula no puede ser nulo.");
		if (nombre.equals(""))
			throw new IllegalArgumentException("El nombre del aula no puede estar vacío.");
		this.nombre = nombre;
	}

	/**
	 * M�todo get para el nombre del aula
	 *
	 * @return el nombre del aula
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * M�todo set para el n�mero de asientos de un aula
	 *
	 * @param puestos
	 *            los n�mero de asientos de un aula
	 * @throws IllegalArgumentException
	 *             si el n�mero de puestos no est�n entre el m�ximo y el m�nimo
	 */
	private void setPuestos(int puestos) throws IllegalArgumentException {
		if (puestos < MIN_PUESTOS || puestos > MAX_PUESTOS)
			throw new IllegalArgumentException("El número de puestos no es correcto.");
		this.puestos = puestos;
	}

	/**
	 * M�todo get para las plazas de un aula
	 *
	 * @return las plazas del aula
	 */
	public int getPuestos() {
		return this.puestos;
	}

	/**
	 * M�todo que calcula los puntos que resta reservar el aula
	 *
	 * @return la cantidad de puntos que cuesta reservar el aula
	 */
	public float getPuntos() {
		return getPuestos() * PUNTOS_POR_PUESTO;
	}

	/**
	 * M�todo hashCode de la clase. Sirve para diferenciar objetos
	 *
	 * @return el c�digo hash del objeto
	 */
	public int hashCode() {
		return Objects.hash(nombre);
	}

	/**
	 * M�todo equals de la clase
	 *
	 * @return True si son iguales, False si no
	 */
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Aula))
			return false;
		Aula otra = (Aula) o;
		if (this.getNombre().equals(otra.getNombre()) && this.hashCode() == otra.hashCode())
			return true;
		return false;
	}

	/**
	 * Representa un aula como una cadena de caracteres
	 *
	 * @return la representaci�n del aula
	 */
	public String toString() {
		return "[nombre=" + nombre + ", puestos=" + puestos + "]";
	}
}
