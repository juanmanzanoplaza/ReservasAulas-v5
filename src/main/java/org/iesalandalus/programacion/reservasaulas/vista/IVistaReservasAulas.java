package org.iesalandalus.programacion.reservasaulas.vista;

import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;
import org.iesalandalus.programacion.reservasaulas.vista.iutextual.VistaReservasAulas;

/**
 * Interfaz de la clase VistaReservasAulas
 *
 * @see VistaReservasAulas
 * @author Juan Antonio Manzano Plaza
 * @version 3
 *
 */
public interface IVistaReservasAulas {

	void setControlador(IControladorReservasAulas controlador);

	void comenzar();

	void salir();

	void insertarAula();

	void borrarAula();

	void buscarAula();

	void listarAulas();

	void insertarProfesor();

	void borrarProfesor();

	void buscarProfesor();

	void listarProfesores();

	void realizarReserva();

	void anularReserva();

	void listarReservas();

	void listarReservasAula();

	void listarReservasProfesor();

	void listarReservasPermanencia();

	void consultarDisponibilidad();

}