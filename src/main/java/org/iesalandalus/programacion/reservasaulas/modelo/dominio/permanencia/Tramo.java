package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;

import java.io.Serializable;

/**
 * Clase enumerada para los distintos tramos de la permanencia
 * 
 * @see PermanenciaPorTramo
 * @author Juan Antonio Manzano Plaza
 * @version 3
 */
public enum Tramo implements Serializable{
	MANANA("Mañana"), TARDE("Tarde");
	private String cadenaAMostrar;

	/**
	 * Constructor privado
	 * 
	 * @param cadenaAMostrar
	 *            la representaci�n del tramo como cadena de caracteres
	 */
	private Tramo(String cadenaAMostrar) {
		this.cadenaAMostrar = cadenaAMostrar;
	}

	/**
	 * Representa un tramo como una cadena de caracteres
	 * 
	 * @return la representaci�n del tramo
	 */
	public String toString() {
		return cadenaAMostrar;
	}

}
