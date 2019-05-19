package org.iesalandalus.programacion.reservasaulas.modelo.ficheros.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.ficheros.ModeloReservasAulas;

/**
 * Clase que guarda y define las operaciones que se pueden realizar sobre un
 * conjunto de profesores
 *
 * @see Profesor
 * @see ModeloReservasAulas
 * @author Juan Antonio Manzano Plaza
 * @version 4
 *
 */
public class Profesores {

	private static final String NOMBRE_FICHERO_PROFESORES = ".\\ficheros";
	private List<Profesor> coleccionProfesores;

	/**
	 * Constructor por defecto. Inicializa la colección de profesores.
	 */
	public Profesores() {
		coleccionProfesores = new ArrayList<Profesor>();
	}

	/**
	 * Constructor copia. Realiza copia profunda para evitar aliasing
	 *
	 * @param profesores
	 *            el objeto del que obtener los datos para inicializar
	 * @throws IllegalArgumentException
	 *             si recibe un conjunto de profesores nulos
	 */
	public Profesores(Profesores profesores) throws IllegalArgumentException {
		setProfesores(profesores);
	}

	/**
	 * Guarda en la colección actual de profesores los que hay en la recibida como
	 * parámetro
	 *
	 * @param profesores
	 *            la colección a copiar
	 * @throws IllegalArgumentException
	 *             si se intenta copiar un conjunto de profesores nulos
	 */
	private void setProfesores(Profesores profesores) throws IllegalArgumentException {
		if (profesores == null)
			throw new IllegalArgumentException("No se pueden copiar profesores nulos.");
		this.coleccionProfesores = copiaProfundaProfesores(profesores.getProfesores());
	}

	/**
	 * Realiza la copia en profundidad de cada profesor para evitar aliasing
	 *
	 * @param profesores
	 *            la colección de profesores a copiar
	 * @return una copia de la colección
	 */
	private List<Profesor> copiaProfundaProfesores(List<Profesor> profesores) {
		List<Profesor> copia = new ArrayList<Profesor>();
		for (Profesor p : profesores)
			copia.add(new Profesor(p));
		return copia;
	}

	/**
	 * Obtiene todos los profesores de la colección actual. Realiza una copia para
	 * evitar aliasing
	 *
	 * @return una copia de la colección
	 */
	public List<Profesor> getProfesores() {
		return copiaProfundaProfesores(this.coleccionProfesores);
	}

	/**
	 * Obtiene el número de profesores que existen en la colección actual
	 *
	 * @return el número de profesores
	 */
	public int getNumProfesores() {
		return this.coleccionProfesores.size();
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
		if (this.coleccionProfesores.contains(profesor))
			throw new OperationNotSupportedException("El profesor ya existe.");
		coleccionProfesores.add(profesor);
	}

	/**
	 * Busca un profesor en la colección
	 *
	 * @param profesor
	 *            el profesor a buscar
	 * @return el profesor buscado o null si no lo encuentra
	 */
	public Profesor buscar(Profesor profesor) {
		if (profesor == null)
			return null;
		if (this.coleccionProfesores.indexOf(profesor) == -1)
			return null;
		return this.coleccionProfesores.get(this.coleccionProfesores.indexOf(profesor));
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
		if (!this.coleccionProfesores.remove(profesor))
			throw new OperationNotSupportedException("El profesor a borrar no existe.");
	}

	/**
	 * Obtiene las salidas de todos los profesores de la colección
	 *
	 * @return la salida de los profesores
	 */
	public List<String> representar() {
		List<String> representar = new ArrayList<String>();
		for (Profesor p : this.coleccionProfesores)
			representar.add(p.toString());
		return representar;
	}

	/**
	 * Lee de fichero los profesores guardados. Si el fichero no existe lo crea.
	 */
	public void leer() {
		try {
			Profesor profesor;
			File f = new File(NOMBRE_FICHERO_PROFESORES);
			f.mkdir();
			f = new File(NOMBRE_FICHERO_PROFESORES + "\\profesores.dat");
			f.createNewFile();
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = null;

			try {
				ois = new ObjectInputStream(fis);
				while(true) {
					profesor = (Profesor) ois.readObject();
					coleccionProfesores.add(profesor);
				}
			} catch (EOFException eof) {
				ois.close();
				System.out.println("Lectura correcta del fichero profesores.dat");
			}
		} catch (Exception e) {
			System.out.println("Error en la lectura del fichero profesores.dat");
		}
	}

	/**
	 * Escribe en fichero las aulas guardadas
	 */
	public void escribir() {
		try {
			File f = new File(NOMBRE_FICHERO_PROFESORES + "\\profesores.dat");
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for(Profesor p : coleccionProfesores)
				oos.writeObject(p);
			oos.close();
		} catch (Exception e) {
			System.out.println("Error en la escritura del fichero profesores.dat");
		}
	}
}
