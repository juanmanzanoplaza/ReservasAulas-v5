package org.iesalandalus.programacion.reservasaulas.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.IModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.ModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.vista.IVistaReservasAulas;
import org.iesalandalus.programacion.reservasaulas.vista.iutextual.VistaReservasAulas;

/**
 * Clase controlador. Tiene el modelo y la vista de la aplicación como
 * atributos, y llama a los métodos del que corresponda de cada uno.
 *
 * @see ModeloReservasAulas
 * @see VistaReservasAulas
 * @author Juan Antonio Manzano Plaza
 * @version 3
 *
 */
public class ControladorReservasAulas implements IControladorReservasAulas {

	private IModeloReservasAulas modelo;
	private IVistaReservasAulas vista;

	/**
	 * Inicializa el controlador y guarda en los atributos la vista y el modelo
	 * obtenidos como parámetros
	 *
	 * @param modelo
	 *            el modelo de la aplicación
	 * @param vista
	 *            la vista de la aplicación
	 */
	public ControladorReservasAulas(IModeloReservasAulas modelo, IVistaReservasAulas vista) {
		this.modelo = modelo;
		this.vista = vista;
		vista.setControlador(this);
	}

	/**
	 * Carga los datos de los archivos llamando a los métodos del modelo y llama a el método comenzar de la vista
	 */
	public void comenzar() {
		modelo.leerAulas();
		modelo.leerProfesores();
		modelo.leerReservas();
		vista.comenzar();
	}

	/**
	 * Guarda los datos de la aplicaci�n en ficheros llamando a los m�todos del modelo
	 */
	public void salir() {
		modelo.escribirAulas();
		modelo.escribirProfesores();
		modelo.escribirReservas();
	}

	/**
	 * Obtiene una lista con todas las aulas guardadas
	 * @return una lista de aulas
	 */
	public List<Aula> getAulas() {
		return modelo.getAulas();
	};

	/**
	 * Llama al m�todo insertarAula del modelo con el aula obtenida por par�metro
	 *
	 * @param insertar
	 *            el aula que queremos insertar
	 * @throws IllegalArgumentException
	 *             si el aula es nula
	 * @throws OperationNotSupportedException
	 *             si el aula ya existe
	 */
	public void insertarAula(Aula insertar) throws OperationNotSupportedException, IllegalArgumentException {
		modelo.insertarAula(insertar);
	}

	/**
	 * Llama al m�todo borrarAula del modelo con el aula obtenida por par�metro
	 *
	 * @param borrar
	 *            el aula a borrar
	 * @throws IllegalArgumentException
	 *             si el aula es nula
	 * @throws OperationNotSupportedException
	 *             si el aula a borrar no existe
	 */
	public void borrarAula(Aula borrar) throws OperationNotSupportedException, IllegalArgumentException {
		modelo.borrarAula(borrar);
	}

	/**
	 * Llama al m�todo buscarAula del modelo con el aula obtenida por par�metro
	 *
	 * @param buscar
	 *            el aula a buscar
	 * @return el aula buscada o null si no existe
	 */
	public Aula buscarAula(Aula buscar) {
		return modelo.buscarAula(buscar);
	}

	/**
	 * Llama al m�todo representarAulas del modelo
	 *
	 * @return una colecci�n con las representaciones de todas las aulas guardadas
	 */
	public List<String> representarAulas() {
		return modelo.representarAulas();
	}

	/**
	 * Obtiene una lista con todos los profesores guardados
	 * @return una lista de profesores
	 */
	public List<Profesor> getProfesores() {
		return modelo.getProfesores();
	}

	/**
	 * Llama al m�todo insertarProfesor del modelo con el profesor obtenido por
	 * par�metro
	 *
	 * @param insertar
	 *            el profesor a insertar
	 * @throws IllegalArgumentException
	 *             si el profesor es nulo
	 * @throws OperationNotSupportedException
	 *             si el profesor ya existe
	 */
	public void insertarProfesor(Profesor insertar) throws OperationNotSupportedException, IllegalArgumentException {
		modelo.insertarProfesor(insertar);
	}

	/**
	 * Llama al m�todo borrarProfesor del modelo con el profesor obtenido por
	 * par�metro
	 *
	 * @param borrar
	 *            el profesor a borrar
	 * @throws IllegalArgumentException
	 *             si el profesor es nulo
	 * @throws OperationNotSupportedException
	 *             si el profesor no existe
	 */
	public void borrarProfesor(Profesor borrar) throws OperationNotSupportedException, IllegalArgumentException {
		modelo.borrarProfesor(borrar);
	}

	/**
	 * Llama al m�todo buscarProfesor del modelo con el profesor obtenido por
	 * par�metro
	 *
	 * @param buscar
	 *            el profesor a buscar
	 * @return el profesor buscado o null si no existe
	 */
	public Profesor buscarProfesor(Profesor buscar) {
		return modelo.buscarProfesor(buscar);
	}

	/**
	 * Llama al m�todo representarProfesores del modelo
	 *
	 * @return una colecci�n con la representaci�n de cada profesor en forma de
	 *         cadena
	 */
	public List<String> representarProfesores() {
		return modelo.representarProfesores();
	}

	public List<Reserva> getReservas() {
		return modelo.getReservas();
	}

	/**
	 * Llama al m�todo realizarReserva del modelo
	 *
	 * @param realizar
	 *            la reserva a realizar
	 * @throws IllegalArgumentException
	 *             si la reserva es nula
	 * @throws OperationNotSupportedException
	 *             si la reserva ya existe
	 */
	public void realizarReserva(Reserva realizar) throws OperationNotSupportedException, IllegalArgumentException {
		modelo.realizarReserva(realizar);
	}

	/**
	 * Llama al m�todo anularReserva del modelo
	 *
	 * @param anular
	 *            la reserva a anular
	 * @throws IllegalArgumentException
	 *             si la reserva es nula
	 * @throws OperationNotSupportedException
	 *             si la reserva no existe
	 */
	public void anularReserva(Reserva anular) throws OperationNotSupportedException, IllegalArgumentException {
		modelo.anularReserva(anular);
	}

	/**
	 * Llama al m�todo representarReservas del modelo
	 *
	 * @return una colecci�n con la representaci�n de cada reserva en forma de
	 *         cadena
	 */
	public List<String> representarReservas() {
		return modelo.representarReservas();
	}

	/**
	 * Llama al m�todo getReservasAula del modelo
	 *
	 * @param aula
	 *            el aula reservada
	 * @return una colecci�n con las reservas realizadas sobre el aula recibida como
	 *         par�metro
	 * @throws IllegalArgumentException
	 *             si el aula es nula
	 */
	public List<Reserva> getReservasAula(Aula aula) throws IllegalArgumentException {
		return modelo.getReservasAula(aula);
	}

	/**
	 * Llama al m�todo getReservasProfesor del modelo
	 *
	 * @param profesor
	 *            el profesor a nombre del que est�n las reservas
	 * @return una colecci�n con las reservas realizadas a nombre del profesor
	 *         recibido como par�metro
	 * @throws IllegalArgumentException
	 *             si el profesor es nulo
	 */
	public List<Reserva> getReservasProfesor(Profesor profesor) throws IllegalArgumentException {
		return modelo.getReservasProfesor(profesor);
	}

	/**
	 * Llama al m�todo getReservasPermanencia del modelo
	 *
	 * @param permanencia
	 *            la permanencia para la que est�n hechas las reservas
	 * @return una colecci�n con las reservas realizadas durante la permanencia
	 *         recibida como par�metro
	 * @throws IllegalArgumentException
	 *             si la permanencia es nula
	 */
	public List<Reserva> getReservasPermanencia(Permanencia permanencia) throws IllegalArgumentException {
		return modelo.getReservasPermanencia(permanencia);
	}

	/**
	 * Llama al m�todo consultarDisponibilidad del modelo
	 *
	 * @param aula
	 *            el aula que queremos comprobar si est� disponible
	 * @param permanencia
	 *            la permanencia sobre la que queremos consultar la disponibilidad
	 * @return true si el aula est� disponible para esa permanencia, false si no lo
	 *         est�
	 * @throws IllegalArgumentException
	 *             si el aula o la permanencia son nulas, o si el tipo de
	 *             permanencia consultado no coincide con el de las reservas
	 *             realizaddas sobre ese aula ese d�a
	 */
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) throws IllegalArgumentException {
		return modelo.consultarDisponibilidad(aula, permanencia);
	}


}
