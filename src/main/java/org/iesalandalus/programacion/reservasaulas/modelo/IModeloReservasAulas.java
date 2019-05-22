package org.iesalandalus.programacion.reservasaulas.modelo;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;

/**
 *
 * Interfaz de la clase ModeloReservasAulas
 *
 * @author Juan Antonio Manzano Plaza
 * @version 5
 *
 */
public interface IModeloReservasAulas {

	public List<Aula> getAulas();

	public int getNumAulas();

	public List<String> representarAulas();

	public Aula buscarAula(Aula buscar);

	public void insertarAula(Aula insertar) throws OperationNotSupportedException, IllegalArgumentException;

	public void borrarAula(Aula borrar) throws OperationNotSupportedException, IllegalArgumentException;

	public void leerAulas();

	public void escribirAulas();

	public List<Profesor> getProfesores();

	public int getNumProfesores();

	public List<String> representarProfesores();

	public Profesor buscarProfesor(Profesor buscar);

	public void insertarProfesor(Profesor insertar) throws OperationNotSupportedException, IllegalArgumentException;

	public void borrarProfesor(Profesor borrar) throws OperationNotSupportedException, IllegalArgumentException;

	public void leerProfesores();

	public void escribirProfesores();

	public List<Reserva> getReservas();

	public int getNumReservas();

	public List<String> representarReservas();

	public Reserva buscarReserva(Reserva buscar);

	public void realizarReserva(Reserva realizar) throws OperationNotSupportedException, IllegalArgumentException;

	public void anularReserva(Reserva anular) throws OperationNotSupportedException, IllegalArgumentException;

	public List<Reserva> getReservasAula(Aula aula) throws IllegalArgumentException;

	public List<Reserva> getReservasProfesor(Profesor profesor) throws IllegalArgumentException;

	public List<Reserva> getReservasPermanencia(Permanencia permanencia) throws IllegalArgumentException;

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) throws IllegalArgumentException;

	public void leerReservas();

	public void escribirReservas();

}