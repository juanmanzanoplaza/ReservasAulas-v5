package org.iesalandalus.programacion.reservasaulas.modelo.mongodb.dao;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.bson.Document;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.ficheros.ModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.mongodb.utilidades.MongoDB;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;

/**
 * Clase que guarda y define las operaciones que se pueden realizar sobre un
 * conjunto de aulas.
 *
 * @see Aula
 * @see ModeloReservasAulas
 * @author Juan Antonio Manzano Plaza
 * @version 5
 *
 */
public class Aulas {

	private static final String COLECCION = "aulas";
	private MongoCollection<Document> coleccionAulas;

	/**
	 * Constructor por defecto.
	 */
	public Aulas() {
		coleccionAulas = MongoDB.getBD().getCollection(COLECCION);
	}

	/**
	 * Obtiene todas las aulas de la colección actual
	 *
	 * @return la colección de aulas
	 */
	public List<Aula> getAulas() {
		List<Aula> aulas = new ArrayList<>();

		for(Document documentoAulas : coleccionAulas.find())
			aulas.add(MongoDB.obtenerAulaDesdeDocumento(documentoAulas));

		return aulas;
	}

	/**
	 * Obtiene el número de aulas que existen en la colección actual
	 *
	 * @return el número de aulas
	 */
	public int getNumAulas() {
		return (int) coleccionAulas.countDocuments();
	}

	/**
	 * Guarda un aula en la colección
	 *
	 * @param aula
	 *            el aula a guardar
	 * @throws IllegalArgumentException
	 *             si el aula es nula
	 * @throws OperationNotSupportedException
	 *             si el aula ya existe
	 */
	public void insertar(Aula aula) throws OperationNotSupportedException, IllegalArgumentException {
		if (aula == null)
			throw new IllegalArgumentException("No se puede insertar un aula nula.");
		if (buscar(aula) != null)
			throw new OperationNotSupportedException("El aula ya existe.");
		coleccionAulas.insertOne(MongoDB.obtenerDocumentoDesdeAula(aula));
	}

	/**
	 * Busca un aula en la colección
	 *
	 * @param aula
	 *            el aula a buscar
	 * @return el aula buscada o null si no la encuentra
	 */
	public Aula buscar(Aula aula) {
		Document documentoAula = coleccionAulas.find().filter(eq(MongoDB.NOMBRE, aula.getNombre())).first();
		return MongoDB.obtenerAulaDesdeDocumento(documentoAula);
	}

	/**
	 * Borra un aula de la colección
	 *
	 * @param aula
	 *            el aula a borrar
	 * @throws IllegalArgumentException
	 *             si el aula es nula
	 * @throws OperationNotSupportedException
	 *             si el aula no existe
	 */
	public void borrar(Aula aula) throws OperationNotSupportedException, IllegalArgumentException {
		if (aula == null)
			throw new IllegalArgumentException("No se puede borrar un aula nula.");
		if (buscar(aula) != null)
			coleccionAulas.deleteOne(eq(MongoDB.NOMBRE, aula.getNombre()));
		else
			throw new OperationNotSupportedException("El aula a borrar no existe.");
	}

	/**
	 * Obtiene las salidas de todas las aulas de la colección
	 *
	 * @return una lista de strings
	 */
	public List<String> representar() {
		List<String> representar = new ArrayList<String>();
		for(Aula aula : getAulas())
			representar.add(aula.toString());
		return representar;
	}

}
