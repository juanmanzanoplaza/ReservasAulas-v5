package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Clase que representa el tiempo que es reservada un aula.
 *
 * @see PermanenciaPorHora
 * @see PermanenciaPorTramo
 * @author Juan Antonio Manzano Plaza
 * @version 4
 *
 */
public abstract class Permanencia implements Serializable {

	protected LocalDate dia;
	protected static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/uuuu");

	/**
	 * NECESARIO PARA LAS LLAMADAS AL SUPER DESDE LAS CLASES HIJAS SIN PARÁMETROS
	 * Puede ser vacío o darle un valor por defecto, es indiferente por que lo
	 * sobreescribiremos en las implementaciones de los constructores de las clases
	 * hijas.
	 */
	protected Permanencia() {
		setDia(LocalDate.now());
	}

	/**
	 * Constructor de la clase
	 *
	 * @param dia
	 *            el día de la reserva
	 * @throws IllegalArgumentException
	 *             si el día de la permanencia no es válido
	 */
	protected Permanencia(LocalDate dia) throws IllegalArgumentException {
		setDia(dia);
	}

	/**
	 * Constructor de la clase
	 *
	 * @param dia
	 *            la fecha de la permanencia
	 * @throws IllegalArgumentException
	 *             si el día de la permanencia no es válido
	 */
	protected Permanencia(String dia) throws IllegalArgumentException {
		setDia(dia);
	}

	/**
	 * Método get para el día de la reserva
	 *
	 * @return el día de la reserva
	 */
	public LocalDate getDia() {
		return this.dia;
	}

	/**
	 * Método set para el día de la reserva
	 *
	 * @param dia
	 *            la fecha de la reserva
	 * @throws IllegalArgumentException
	 *             si el d�a es nulo
	 */
	protected void setDia(LocalDate dia) throws IllegalArgumentException {
		if (dia == null)
			throw new IllegalArgumentException("El día de una permanencia no puede ser nulo.");
		this.dia = LocalDate.of(dia.getYear(), dia.getMonth(), dia.getDayOfMonth());
	}

	/**
	 * Método set para el día de la reserva
	 *
	 * @param dia
	 *            la fecha de la reserva
	 * @throws IllegalArgumentException
	 *             si el día es nulo o el formato no es válido
	 */
	protected void setDia(String dia) throws IllegalArgumentException {
		if (dia == null)
			throw new IllegalArgumentException("El día de una permanencia no puede ser nulo.");
		try {
			this.dia = LocalDate.parse(dia, FORMATO_DIA);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("El formato del día de la permanencia no es correcto.");
		}
	}

	/**
	 * Obtiene la cantidad de puntos que cuesta reservar un aula durante una
	 * permanencia determinada.
	 *
	 * @return la cantidad de puntos que cuesta la permanencia
	 */
	public abstract int getPuntos();

	/**
	 * Representa una permanencia como una cadena de caracteres
	 *
	 * @return la representación de la permanencia
	 */
	public abstract String toString();

	/**
	 * Método hashCode de la clase. Sirve para diferenciar objetos
	 *
	 * @return el código hash del objeto
	 */
	public abstract int hashCode();

	/**
	 * Método equals de la clase
	 *
	 * @return True si son iguales, False si no
	 */
	public abstract boolean equals(Object o);
}
