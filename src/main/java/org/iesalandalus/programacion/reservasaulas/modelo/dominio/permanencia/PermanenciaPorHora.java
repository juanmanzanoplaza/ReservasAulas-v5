package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Clase que representa durante cuanto tiempo se reserva un aula. Esta clase
 * permite que se pueda reservar un aula por un tramo de una hora.
 * 
 * @see Permanencia
 * @author Juan Antonio Manzano Plaza
 * @version 4
 *
 */
public class PermanenciaPorHora extends Permanencia implements Serializable {

	private static final int PUNTOS = 3;
	private static final int HORA_INICIO = 8;
	private static final int HORA_FIN = 22;
	private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	private LocalTime hora;

	/**
	 * Constructor de la clase
	 * 
	 * @param dia
	 *            el día que es reservada el aula
	 * @param hora
	 *            la hora durante la que es reservada el aula
	 * @throws IllegalArgumentException
	 *             si el día o la hora no son válidos
	 */
	public PermanenciaPorHora(LocalDate dia, LocalTime hora) throws IllegalArgumentException {
		super(dia);
		setHora(hora);
	}

	/**
	 * Constructor de la clase
	 * 
	 * @param dia
	 *            el día que es reservada el aula
	 * @param hora
	 *            la hora durante la que es reservada el aula
	 * @throws IllegalArgumentException
	 *             si el día o la hora no son válidos
	 */
	public PermanenciaPorHora(String dia, LocalTime hora) throws IllegalArgumentException {
		super(dia);
		setHora(hora);
	}

	/**
	 * Constructor de la clase
	 * 
	 * @param dia
	 *            el día que es reservada el aula
	 * @param hora
	 *            la hora durante la que es reservada el aula
	 * @throws IllegalArgumentException
	 *             si el día o la hora no son válidos
	 */
	public PermanenciaPorHora(LocalDate dia, String hora) throws IllegalArgumentException {
		super(dia);
		setHora(hora);
	}

	/**
	 * Constructor de la clase
	 * 
	 * @param dia
	 *            el día que es reservada el aula
	 * @param hora
	 *            la hora durante la que es reservada el aula
	 * @throws IllegalArgumentException
	 *             si el día o la hora no son válidos
	 */
	public PermanenciaPorHora(String dia, String hora) throws IllegalArgumentException {
		super(dia);
		setHora(hora);
	}

	/**
	 * Constructor copia de la clase
	 * 
	 * @param otra
	 *            la permanencia a copiar
	 * @throws IllegalArgumentException
	 *             si la permanencia a copiar es nula
	 */
	public PermanenciaPorHora(PermanenciaPorHora otra) throws IllegalArgumentException {
		if (otra == null)
			throw new IllegalArgumentException("No se puede copiar una permanencia nula.");
		super.setDia(otra.getDia());
		setHora(otra.getHora());
	}

	/**
	 * Método get para la hora de la permanencia
	 * 
	 * @return la hora de la permanencia
	 */
	public LocalTime getHora() {
		return hora;
	}

	/**
	 * Método set para la hora de la permanencia
	 * 
	 * @param hora
	 *            la hora de la permanencia
	 * @throws IllegalArgumentException
	 *             si la hora no cumple alguna de las restricciones sobre las horas
	 *             o no es válida
	 */
	private void setHora(LocalTime hora) throws IllegalArgumentException {
		if (hora == null)
			throw new IllegalArgumentException("La hora de una permanencia no puede ser nula.");
		if (hora.getHour() < HORA_INICIO || hora.getHour() > HORA_FIN)
			throw new IllegalArgumentException("La hora de una permanencia debe estar comprendida entre las "
					+ HORA_INICIO + " y las " + HORA_FIN + ".");
		if (hora.getMinute() != 0)
			throw new IllegalArgumentException("La hora de una permanencia debe ser una hora en punto.");
		this.hora = hora;
	}

	/**
	 * Método set para la hora de la permanencia
	 * 
	 * @param hora
	 *            la hora de la permanencia
	 * @throws IllegalArgumentException
	 *             si la hora no cumple alguna de las restricciones sobre las horas
	 *             o no es válida
	 */
	private void setHora(String hora) throws IllegalArgumentException {
		if (hora == null)
			throw new IllegalArgumentException("La hora de una permanencia no puede ser nula.");
		LocalTime otraHora = null;
		try {
			otraHora = LocalTime.parse(hora, FORMATO_HORA);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("El formato de la hora de la permanencia no es correcto.");
		}
		if (otraHora.getHour() < HORA_INICIO || otraHora.getHour() > HORA_FIN)
			throw new IllegalArgumentException("La hora de una permanencia debe estar comprendida entre las "
					+ HORA_INICIO + " y las " + HORA_FIN + ".");
		if (otraHora.getMinute() != 0)
			throw new IllegalArgumentException("La hora de una permanencia debe ser una hora en punto.");
		this.hora = otraHora;

	}

	/**
	 * Método get para el número de puntos que cuesta reservar esta Permanencia
	 * 
	 * @return el número de puntos que cuesta reservar esta Permanencia
	 */
	public int getPuntos() {
		return PUNTOS;
	}

	/**
	 * Método hashCode parra la clase. Sirve para diferenciar objetos.
	 * 
	 * @return el código hash correspondiente al objeto
	 */
	public int hashCode() {
		return Objects.hash(dia, hora, PUNTOS);
	}

	/**
	 * Método equals para la clase. Compara dos PermanenciaPorHora
	 * 
	 * @return true si son iguales, false si no lo son
	 */
	public boolean equals(Object o) {
		if (!(o instanceof PermanenciaPorHora))
			return false;
		PermanenciaPorHora otra = (PermanenciaPorHora) o;
		if (this.getDia().equals(otra.getDia()) && this.getHora().equals(otra.getHora()))
			return true;
		return false;
	}

	/**
	 * Método toString de la clase. Define como debe mostrarse una
	 * PermanenciaPorHora
	 * 
	 * @return la representación en forma de texto de la Permanencia
	 */
	public String toString() {
		return "[dia=" + getDia().format(FORMATO_DIA) + ", hora=" + getHora().format(FORMATO_HORA) + "]";
	}

}
