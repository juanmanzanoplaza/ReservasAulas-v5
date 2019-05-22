package org.iesalandalus.programacion.reservasaulas.modelo.mongodb.dao;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.bson.Document;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.ficheros.ModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.mongodb.utilidades.MongoDB;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;

/**
 * Clase que guarda y define las operaciones que se pueden realizar sobre un
 * conjunto de profesores
 *
 * @see Profesor
 * @see ModeloReservasAulas
 * @author Juan Antonio Manzano Plaza
 * @version 5
 *
 */
public class Profesores {

	private static final String COLECCION = "profesores";
	private MongoCollection<Document> coleccionProfesores;

	/**
	 * Constructor por defecto. Inicializa la colección de profesores.
	 */
	public Profesores() {
		coleccionProfesores = MongoDB.getBD().getCollection(COLECCION);
	}

	/**
	 * Obtiene todos los profesores de la colección actual.
	 *
	 * @return la colección de profesores
	 */
	public List<Profesor> getProfesores() {
		List<Profesor> profesores = new ArrayList<>();
		for(Document documentoProfesor : coleccionProfesores.find())
			profesores.add(MongoDB.obtenerProfesorDesdeDocumento(documentoProfesor));
		return profesores;
	}

	/**
	 * Obtiene el número de profesores que existen en la colección actual
	 *
	 * @return el número de profesores
	 */
	public int getNumProfesores() {
		return (int) coleccionProfesores.countDocuments();
	}

	/**
	 * Guarda un profesor en la colección
	 *
	 * @param profesor
	 *            el profesor a guardar
	 * @throws IllegalArgumentException
	 *             si el profesor es nulo
	 * @throws OperationNotSupportedException
	 *             si el profesor ya existe
	 */
	public void insertar(Profesor profesor) throws OperationNotSupportedException, IllegalArgumentException {
		if (profesor == null)
			throw new IllegalArgumentException("No se puede insertar un profesor nulo.");
		if (buscar(profesor) != null)
			throw new OperationNotSupportedException("El profesor ya existe.");
		coleccionProfesores.insertOne(MongoDB.obtenerDocumentoDesdeProfesor(profesor));;
	}

	/**
	 * Busca un profesor en la colección
	 *
	 * @param profesor
	 *            el profesor a buscar
	 * @return el profesor buscado o null si no lo encuentra
	 */
	public Profesor buscar(Profesor profesor) {
		Document documentoProfesor = coleccionProfesores.find().filter(eq(MongoDB.NOMBRE, profesor.getNombre())).first();
		return MongoDB.obtenerProfesorDesdeDocumento(documentoProfesor);
	}

	/**
	 * Borra un profesor de la colección
	 *
	 * @param profesor
	 *            el profesor a borrar
	 * @throws IllegalArgumentException
	 *             si el profesor es nulo
	 * @throws OperationNotSupportedException
	 *             si el profesor no existe
	 */
	public void borrar(Profesor profesor) throws OperationNotSupportedException, IllegalArgumentException {
		if (profesor == null)
			throw new IllegalArgumentException("No se puede borrar un profesor nulo.");
		if (buscar(profesor) != null)
			coleccionProfesores.deleteOne(eq(MongoDB.NOMBRE, profesor.getNombre()));
		else
			throw new OperationNotSupportedException("El profesor a borrar no existe.");
	}

	/**
	 * Obtiene las salidas de todos los profesores de la colección
	 *
	 * @return una lista de strings
	 */
	public List<String> representar() {
		List<String> representar = new ArrayList<String>();
		for (Profesor profesor : getProfesores())
			representar.add(profesor.toString());
		return representar;
	}
}
