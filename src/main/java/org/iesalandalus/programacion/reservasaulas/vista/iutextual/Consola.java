package org.iesalandalus.programacion.reservasaulas.vista.iutextual;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

/**
 *
 * Clase dedicada a la interacción con el usuario. Pide y lee los datos por
 * teclado
 *
 * @see VistaReservasAulas
 * @see Opcion
 * @author Juan Antonio Manzano Plaza
 * @version 4
 *
 */
public class Consola {

	/**
	 * Constructor privado para evitar que se instancien objetos de la clase.
	 */
	private Consola() {
	}

	/**
	 * Método estático encargado de mostrar por pantalla todas las opciones de
	 * Opcion
	 */
	public static void mostrarMenu() {
		System.out.println("*************************************************************");
		System.out.println("*           RESERVAS DE AULAS DEL IES AL-ÁNDALUS            *");
		System.out.println("*************************************************************");
		int i = 0;
		for (Opcion o : Opcion.values()) {
			System.out.printf("* %-2d- %-54s*\n", i, o.getMensaje());
			i++;
		}
		System.out.println("*************************************************************");
	}

	/**
	 * Imprime por pantalla el título de la opción seleccionada.
	 *
	 * @param cabecera
	 *            el título de la opción
	 */
	public static void mostrarCabecera(String cabecera) {
		System.out.println(cabecera);
	}

	/**
	 * Lee por consola el valor ordinal de la opción que desea realizarse.
	 *
	 * @return el valor ordinal de la opción a realizar
	 */
	public static int elegirOpcion() {
		int opcion;
		System.out.println("¿Qué opción desea elegir?");
		opcion = Entrada.entero();
		return opcion;
	}

	/**
	 * Lee por consola el nombre de un aula y la crea.
	 *
	 * @return el aula leída
	 */
	public static Aula leerAula() {
		Aula leida = null;
		String nombre = leerNombreAula();
		System.out.println("Introduzca el número de puestos del aula.");
		int puestos = Entrada.entero();
		leida = new Aula(nombre, puestos);
		System.out.println("Aula leída correctamente.");
		return leida;
	}

	/**
	 * Lee por consola el nombre de un aula.
	 *
	 * @return el nombre leído
	 */
	public static String leerNombreAula() {
		String nombre;
		System.out.println("Introduzca el nombre del aula.");
		nombre = Entrada.cadena();
		return nombre;
	}

	/**
	 * Lee por consola todos los atributos de un profesor y lo crea.
	 *
	 * @return el profesor leído
	 */
	public static Profesor leerProfesor() {
		Profesor leido = null;
		String nombre = leerNombreProfesor();
		System.out.println("Introduzca el correo electrónico del profesor.");
		String correo = Entrada.cadena();
		System.out.println("Introduzca el teléfono del profesor. (Puede dejarse vacío)");
		String telefono = Entrada.cadena();
		if (telefono.equals(""))
			leido = new Profesor(nombre, correo);
		else
			leido = new Profesor(nombre, correo, telefono);
		System.out.println("Profesor leído correctamente.");
		return leido;
	}

	/**
	 * Lee por consola el nombre de un profesor.
	 *
	 * @return el nombre leído
	 */
	public static String leerNombreProfesor() {
		String nombre;
		System.out.println("Introduzca el nombre del profesor.");
		nombre = Entrada.cadena();
		return nombre;
	}

	/**
	 * Lee por consola un tramo. En esta versión sólo están las opciones de mañana
	 * (0) y de tarde (1) Si añadimos más opciones a Tramo será necesario cambiar la
	 * salida por pantalla, pero no el bucle ni el return.
	 *
	 * @return el tramo horario leído
	 */
	public static Tramo leerTramo() {
		int opcion;
		do {
			System.out.println("¿Tramo de mañana (0) o de tarde (1)?");
			opcion = Entrada.entero();
		} while (opcion < 0 || opcion > Tramo.values().length - 1);
		return Tramo.values()[opcion];

	}

	/**
	 * Lee por consola el día para una permanencia
	 *
	 * @return la fecha leída
	 */
	public static String leerDia() {
		String dia = "";
		System.out.println("Introduzca una fecha en el formato \"dd/mm/aaaa\".");
		dia = Entrada.cadena();
		return dia;
	}

	/**
	 * Lee por consola la hora para una permanencia por hora
	 *
	 * @return la hora leída
	 */
	public static String leerHora() {
		String hora = "";
		System.out.println("Introduzca una hora en el formato hh:mm");
		hora = Entrada.cadena();
		return hora;
	}

	/**
	 * Lee por consola el tipo de permanencia que se desea leer
	 *
	 * @return el tipo de permanencia elegido
	 */
	public static int elegirPermanencia() {
		int permanencia = -1;
		do {
			System.out.println("¿Por horas (0) o por tramo (1)?");
			permanencia = Entrada.entero();
		} while (permanencia < 0 || permanencia > 1);
		return permanencia;
	}

}
