package org.iesalandalus.programacion.reservasaulas.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;

/**
 *
 * Interfaz de la clase ControladorReservasAulas
 *
 * @see ControladorReservasAulas
 * @author Juan Antonio Manzano Plaza
 * @version 4
 *
 */
public interface IControladorReservasAulas {

	public void comenzar();

	public void salir();

	public List<Aula> getAulas();

	public void insertarAula(Aula insertar) throws OperationNotSupportedException, IllegalArgumentException;

	public void borrarAula(Aula borrar) throws OperationNotSupportedException, IllegalArgumentException;

	public Aula buscarAula(Aula buscar);

	public List<String> representarAulas();

	public List<Profesor> getProfesores();

	public void insertarProfesor(Profesor insertar) throws OperationNotSupportedException, IllegalArgumentException;

	public void borrarProfesor(Profesor borrar) throws OperationNotSupportedException, IllegalArgumentException;

	public Profesor buscarProfesor(Profesor buscar);

	public List<String> representarProfesores();

	public List<Reserva> getReservas();

	public void realizarReserva(Reserva realizada) throws OperationNotSupportedException, IllegalArgumentException;

	public void anularReserva(Reserva anulada) throws OperationNotSupportedException, IllegalArgumentException;

	public List<String> representarReservas();

	public List<Reserva> getReservasAula(Aula aula) throws IllegalArgumentException;

	public List<Reserva> getReservasProfesor(Profesor profesor) throws IllegalArgumentException;

	public List<Reserva> getReservasPermanencia(Permanencia permanencia) throws IllegalArgumentException;

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) throws IllegalArgumentException;


}