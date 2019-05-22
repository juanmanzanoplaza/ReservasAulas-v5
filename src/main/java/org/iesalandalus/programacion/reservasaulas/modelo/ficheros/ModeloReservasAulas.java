package org.iesalandalus.programacion.reservasaulas.modelo.ficheros;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.controlador.ControladorReservasAulas;
import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.IModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.ficheros.dao.Aulas;
import org.iesalandalus.programacion.reservasaulas.modelo.ficheros.dao.Profesores;
import org.iesalandalus.programacion.reservasaulas.modelo.ficheros.dao.Reservas;

/**
 *
 * Clase modelo utilizada como intermediaria entre el controlador y las clases
 * del modelo. Llama a todas las funciones de la aplicación.
 *
 * @see Profesores
 * @see Aulas
 * @see Reservas
 * @see ControladorReservasAulas
 * @see IControladorReservasAulas
 * @author Juan Antonio Manzano Plaza
 * @version 5
 *
 */
public class ModeloReservasAulas implements IModeloReservasAulas {

	private Profesores profesores;
	private Aulas aulas;
	private Reservas reservas;

	/**
	 * Constructor de la clase. Crea los distintos atributos de la clase.
	 */
	public ModeloReservasAulas() {
		this.profesores = new Profesores();
		this.aulas = new Aulas();
		this.reservas = new Reservas();
	}

	/**
	 * Obtiene todas las aulas registradas. Llama al método getAulas de Aulas
	 *
	 * @return todas las aulas guardadas
	 */
	public List<Aula> getAulas() {
		return aulas.getAulas();
	}

	/**
	 * Obtiene el número de aulas registradas. Llama al método getNumAulas de Aulas
	 *
	 * @return el número de aulas guardadas
	 */
	public int getNumAulas() {
		return aulas.getNumAulas();
	}

	/**
	 * Obtiene la salida de todas las aulas registradas. Llama al método representar
	 * de Aulas
	 *
	 * @return una colección con la representación de cada aula en forma de cadena
	 */
	public List<String> representarAulas() {
		return aulas.representar();
	}

	/**
	 * Busca el aula indicada entre las que han sido registradas. Llama al método
	 * buscar de Aulas
	 *
	 * @param buscar
	 *            el aula que estamos buscando
	 * @return el aula buscada o null si no la encuentra
	 */
	public Aula buscarAula(Aula buscar) {
		return aulas.buscar(buscar);
	}

	/**
	 * Guarda el aula indicada. Llama al método insertar de Aulas
	 *
	 * @param insertar
	 *            el aula que queremos guardar
	 * @throws IllegalArgumentException
	 *             si el aula es nula
	 * @throws OperationNotSupportedException
	 *             si el aula ya existe
	 */
	public void insertarAula(Aula insertar) throws OperationNotSupportedException, IllegalArgumentException {
		aulas.insertar(insertar);
	}

	/**
	 * Borra el aula indicada si existe entre las guardadas. Llama al método borrar
	 * de Aulas
	 *
	 * @param borrar
	 *            el aula que queremos borrar
	 * @throws IllegalArgumentException
	 *             si el aula es nula
	 * @throws OperationNotSupportedException
	 *             si el aula no existe
	 */
	public void borrarAula(Aula borrar) throws OperationNotSupportedException, IllegalArgumentException {
		aulas.borrar(borrar);
		// borrar reservas sobre ese aula
		List<Reserva> reservasAula = reservas.getReservasAula(borrar);
		for (Reserva r : reservasAula)
			reservas.borrar(r);
	}

	/**
	 * Lee de fichero las aulas guardadas. Si el fichero no existe lo crea.
	 */
	public void leerAulas() {
		aulas.leer();
	}

	/**
	 * Escribe en fichero las aulas guardadas.
	 */
	public void escribirAulas() {
		aulas.escribir();
	}

	/**
	 * Obtiene todos los profesores registrados
	 *
	 * @return todos los profesores guardados
	 */
	public List<Profesor> getProfesores() {
		return profesores.getProfesores();
	}

	/**
	 * Obtiene el número de profesores registrados
	 *
	 * @return el número de profesores guardados
	 */
	public int getNumProfesores() {
		return profesores.getNumProfesores();
	}

	/**
	 * Obtiene la salida de todos los profesores registrados
	 *
	 * @return una colección con la representación de cada profesor en forma de
	 *         cadena
	 */
	public List<String> representarProfesores() {
		return profesores.representar();
	}

	/**
	 * Busca el profesor indicado entre los que han sido registrados
	 *
	 * @param buscar
	 *            el profesor que buscamos
	 * @return el profesor buscado o null si no lo encuentra
	 */
	public Profesor buscarProfesor(Profesor buscar) {
		return profesores.buscar(buscar);
	}

	/**
	 * Guarda el profesor indicado
	 * 
	 * @param insertar
	 *            el profesor que queremos guardar
	 * @throws IllegalArgumentException
	 *             si el profesor es nulo
	 * @throws OperationNotSupportedException
	 *             si el profesor ya existe
	 */
	public void insertarProfesor(Profesor insertar) throws OperationNotSupportedException, IllegalArgumentException {
		profesores.insertar(insertar);
	}

	/**
	 * Borra el profesor indicado si existe entre los que han sido registrados.
	 *
	 * @param borrar
	 *            el profesor que queremos borrar
	 * @throws IllegalArgumentException
	 *             si el profesor es nulo
	 * @throws OperationNotSupportedException
	 *             si el profesor no existe
	 */
	public void borrarProfesor(Profesor borrar) throws OperationNotSupportedException, IllegalArgumentException {
		profesores.borrar(borrar);
		// borrar reservas a nombre de ese profesor
		List<Reserva> reservasProfesor = reservas.getReservasProfesor(borrar);
		for (Reserva r : reservasProfesor)
			reservas.borrar(r);
	}

	/**
	 * Lee de fichero los profesores guardados. Si el fichero no existe lo crea.
	 */
	public void leerProfesores() {
		profesores.leer();
	}

	/**
	 * Escribe en fichero los profesores guardados.
	 */
	public void escribirProfesores() {
		profesores.escribir();
	}

	/**
	 * Obtiene todas las reservas realizadas
	 *
	 * @return todas las reservas realizadas
	 */
	public List<Reserva> getReservas() {
		return reservas.getReservas();
	}

	/**
	 * Obtiene el número de reservas realizadas
	 *
	 * @return el número de reservas guardadas
	 */
	public int getNumReservas() {
		return reservas.getNumReservas();
	}

	/**
	 * Obtiene la salida de todas las reservas realizadas
	 *
	 * @return una colección con la representación de cada reserva en forma de
	 *         cadena
	 */
	public List<String> representarReservas() {
		return reservas.representar();
	}

	/**
	 * Busca la reserva indicada entre las que se han realizado
	 *
	 * @param buscar
	 *            la reserva que buscamos
	 * @return la reserva buscada o null si no la encuentra
	 */
	public Reserva buscarReserva(Reserva buscar) {
		return reservas.buscar(buscar);
	}

	/**
	 * Guarda la reserva indicada
	 * 
	 * @param realizar
	 *            la reserva a realizar
	 * @throws IllegalArgumentException
	 *             si la reserva es nula
	 * @throws OperationNotSupportedException
	 *             si la reserva ya existe
	 */
	public void realizarReserva(Reserva realizar) throws OperationNotSupportedException, IllegalArgumentException {
		reservas.insertar(realizar);
	}

	/**
	 * Borra la reserva indicada si existe entre las realizadas
	 *
	 * @param anular
	 *            la reserva a anular
	 * @throws IllegalArgumentException
	 *             si la reserva es nula
	 * @throws OperationNotSupportedException
	 *             si la reserva no existe
	 */
	public void anularReserva(Reserva anular) throws OperationNotSupportedException, IllegalArgumentException {
		reservas.borrar(anular);
	}

	/**
	 * Obtiene todas las reservas correspondientes al aula indicada
	 *
	 * @param aula
	 *            el aula sobre la que están hechas las reservas
	 * @return una lista con todas las reservas sobre el aula indicada
	 * @throws IllegalArgumentException
	 *             si el aula es nula
	 */
	public List<Reserva> getReservasAula(Aula aula) throws IllegalArgumentException {
		return reservas.getReservasAula(aula);
	}

	/**
	 * Obtiene todas las reservas realizadas por el profesor indicado
	 *
	 * @param profesor
	 *            el profesor a nombre del que están hechas las reservas
	 * @return una lista con todas las reservas a nombre del profesor indicado
	 * @throws IllegalArgumentException
	 *             si el profesor es nulo
	 */
	public List<Reserva> getReservasProfesor(Profesor profesor) throws IllegalArgumentException {
		return reservas.getReservasProfesor(profesor);
	}

	/**
	 * Obtiene todas las reservas realizadas en un día y tramo indicados
	 *
	 * @param permanencia
	 *            la fecha de las reservas
	 * @return una lista con todas las reservas de ese día y tramo
	 * @throws IllegalArgumentException
	 *             si la permanencia es nula
	 */
	public List<Reserva> getReservasPermanencia(Permanencia permanencia) throws IllegalArgumentException {
		return reservas.getReservasPermanencia(permanencia);
	}

	/**
	 * Comprueba si hay alguna reserva realizada sobre un aula en una permanencia
	 * indicadas
	 *
	 * @param aula
	 *            el aula sobre la que queremos consultar la disponibilidad
	 * @param permanencia
	 *            el día que queremos comprobar si está reservada el aula
	 * @return True si está disponible (no está reservada) y False si no está
	 *         disponible (está reservada)
	 * @throws IllegalArgumentException
	 *             si el aula o la permanencia son nulas, o si el tipo de
	 *             permanencia consultado no coincide con el de las reservas
	 *             realizadas sobre ese aula ese día
	 */
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) throws IllegalArgumentException {
		return reservas.consultarDisponibilidad(aula, permanencia);
	}

	/**
	 * Lee de fichero las reservas guardadas. Si el fichero no existe lo crea.
	 */
	public void leerReservas() {
		reservas.leer();
	}

	/**
	 * Escribe en fichero las reservas guardadas. Si el fichero no existe lo crea.
	 */
	public void escribirReservas() {
		reservas.escribir();
	}

}
