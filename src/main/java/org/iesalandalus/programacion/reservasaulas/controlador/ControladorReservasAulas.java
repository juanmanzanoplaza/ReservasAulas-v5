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
 * @version 4
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
	 * Guarda los datos de la aplicación en ficheros llamando a los métodos del modelo
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
	 * Llama al método insertarAula del modelo con el aula obtenida por parámetro
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
	 * Llama al método borrarAula del modelo con el aula obtenida por parámetro
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
	 * Llama al método buscarAula del modelo con el aula obtenida por parámetro
	 *
	 * @param buscar
	 *            el aula a buscar
	 * @return el aula buscada o null si no existe
	 */
	public Aula buscarAula(Aula buscar) {
		return modelo.buscarAula(buscar);
	}

	/**
	 * Llama al método representarAulas del modelo
	 *
	 * @return una colección con las representaciones de todas las aulas guardadas
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
	 * Llama al método insertarProfesor del modelo con el profesor obtenido por
	 * parámetro
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
	 * Llama al método borrarProfesor del modelo con el profesor obtenido por
	 * parámetro
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
	 * Llama al método buscarProfesor del modelo con el profesor obtenido por
	 * parámetro
	 *
	 * @param buscar
	 *            el profesor a buscar
	 * @return el profesor buscado o null si no existe
	 */
	public Profesor buscarProfesor(Profesor buscar) {
		return modelo.buscarProfesor(buscar);
	}

	/**
	 * Llama al método representarProfesores del modelo
	 *
	 * @return una colección con la representación de cada profesor en forma de
	 *         cadena
	 */
	public List<String> representarProfesores() {
		return modelo.representarProfesores();
	}

	public List<Reserva> getReservas() {
		return modelo.getReservas();
	}

	/**
	 * Llama al método realizarReserva del modelo
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
	 * Llama al método anularReserva del modelo
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
	 * Llama al método representarReservas del modelo
	 *
	 * @return una colección con la representación de cada reserva en forma de
	 *         cadena
	 */
	public List<String> representarReservas() {
		return modelo.representarReservas();
	}

	/**
	 * Llama al método getReservasAula del modelo
	 *
	 * @param aula
	 *            el aula reservada
	 * @return una colección con las reservas realizadas sobre el aula recibida como
	 *         parámetro
	 * @throws IllegalArgumentException
	 *             si el aula es nula
	 */
	public List<Reserva> getReservasAula(Aula aula) throws IllegalArgumentException {
		return modelo.getReservasAula(aula);
	}

	/**
	 * Llama al método getReservasProfesor del modelo
	 *
	 * @param profesor
	 *            el profesor a nombre del que están las reservas
	 * @return una colección con las reservas realizadas a nombre del profesor
	 *         recibido como parámetro
	 * @throws IllegalArgumentException
	 *             si el profesor es nulo
	 */
	public List<Reserva> getReservasProfesor(Profesor profesor) throws IllegalArgumentException {
		return modelo.getReservasProfesor(profesor);
	}

	/**
	 * Llama al método getReservasPermanencia del modelo
	 *
	 * @param permanencia
	 *            la permanencia para la que están hechas las reservas
	 * @return una colección con las reservas realizadas durante la permanencia
	 *         recibida como parámetro
	 * @throws IllegalArgumentException
	 *             si la permanencia es nula
	 */
	public List<Reserva> getReservasPermanencia(Permanencia permanencia) throws IllegalArgumentException {
		return modelo.getReservasPermanencia(permanencia);
	}

	/**
	 * Llama al método consultarDisponibilidad del modelo
	 *
	 * @param aula
	 *            el aula que queremos comprobar si está disponible
	 * @param permanencia
	 *            la permanencia sobre la que queremos consultar la disponibilidad
	 * @return true si el aula está disponible para esa permanencia, false si no lo
	 *         está
	 * @throws IllegalArgumentException
	 *             si el aula o la permanencia son nulas, o si el tipo de
	 *             permanencia consultado no coincide con el de las reservas
	 *             realizaddas sobre ese aula ese día
	 */
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) throws IllegalArgumentException {
		return modelo.consultarDisponibilidad(aula, permanencia);
	}


}
