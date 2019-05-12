package org.iesalandalus.programacion.reservasaulas.vista.iutextual;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.ModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.vista.IVistaReservasAulas;

/**
 *
 * Clase encargada de tratar las excepciones y llamar a las distintas funciones
 * del modelo ModeloReservasAulas
 *
 * @see ModeloReservasAulas
 * @author Juan Antonio Manzano Plaza
 * @version 3
 *
 */
public class VistaReservasAulas implements IVistaReservasAulas {

	// Previo al mensaje del error.
	private static final String ERROR = "ERROR: ";
	// private static final String NOMBRE_VALIDO = "Juan";
	private static final String CORREO_VALIDO = "a@a.a";
	private IControladorReservasAulas controlador;

	/**
	 * Constructor de la clase
	 */
	public VistaReservasAulas() {
		Opcion.setVista(this);
	}

	public void setControlador(IControladorReservasAulas controlador) {
		this.controlador = controlador;
	}

	/**
	 * M�todo que inicia el programa y mientras que no se elija la opci�n salir
	 * sigue ofreciendo opciones.
	 */
	public void comenzar() {
		Opcion opcion = null;
		do {
			Consola.mostrarMenu();
			try {
				// Lectura de la opci�n
				opcion = Opcion.getOpcionSegunOrdinal(Consola.elegirOpcion());
				// Ejecuci�n de la opci�n
				opcion.ejecutar();
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(ERROR + "Introduzca un número de opción válido.");
			}
		} while (opcion != Opcion.SALIR);
	}

	/**
	 * Ejecuta la orden salir de Opcion
	 */
	public void salir() {
		controlador.salir();
		System.out.println("Fin de la ejecución.");
	}

	/**
	 * Ejecuta la orden insertarAula de Opcion llamando al m�todo correspondiente de
	 * ModeloReservasAulas
	 */
	public void insertarAula() {
		Consola.mostrarCabecera("INSERTAR AULA");
		try {
			controlador.insertarAula(Consola.leerAula());
			System.out.println("Aula insertada.");
		} catch (Exception e) {
			System.out.println(ERROR + e.getMessage());
			System.out.println("Aula no insertada.");
		}
	}

	/**
	 * Ejecuta la orden borrarAula de Opcion llamando al m�todo correspondiente de
	 * ModeloReservasAulas
	 */
	public void borrarAula() {
		Consola.mostrarCabecera("BORRAR AULA");
		try {
			controlador.borrarAula(new Aula(Consola.leerNombreAula(), 10));
			System.out.println("Aula eliminada.");
		} catch (Exception e) {
			System.out.println(ERROR + e.getMessage());
			System.out.println("No se pudo eliminar el aula.");
		}
	}

	/**
	 * Ejecuta la orden buscarAula de Opcion llamando al m�todo correspondiente de
	 * ModeloReservasAulas
	 */
	public void buscarAula() {
		Consola.mostrarCabecera("BUSCAR AULA");
		Aula leida = null;
		try {
			leida = new Aula(Consola.leerNombreAula(), 10);
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
		Aula buscada = controlador.buscarAula(leida);
		if (buscada == null)
			System.out.println("El aula buscada no existe.");
		else
			System.out.println("Se ha encontrado el aula buscada: " + buscada);
	}

	/**
	 * Ejecuta la orden listarAulas de Opcion llamando al m�todo correspondiente de
	 * ModeloReservasAulas
	 */
	public void listarAulas() {
		Consola.mostrarCabecera("LISTAR AULAS");
		List<String> aulas = controlador.representarAulas();
		if (aulas.size() == 0)
			System.out.println("No hay ningún aula guardada.");
		for (String s : aulas)
			System.out.println(s);
	}

	/**
	 * Ejecuta la orden insertarProfesor de Opcion llamando al m�todo
	 * correspondiente de ModeloReservasAulas
	 */
	public void insertarProfesor() {
		Consola.mostrarCabecera("INSERTAR PROFESOR");
		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("Profesor insertado.");
		} catch (Exception e) {
			System.out.println(ERROR + e.getMessage());
			System.out.println("Profesor no insertado.");
		}
	}

	/**
	 * Ejecuta la orden borrarProfesor de Opcion llamando al m�todo correspondiente
	 * de ModeloReservasAulas
	 */
	public void borrarProfesor() {
		Consola.mostrarCabecera("BORRAR PROFESOR");
		Profesor borrar = null;
		try {
			borrar = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
		} catch (Exception e) {
			System.out.println(ERROR + e.getMessage());
		}
		try {
			controlador.borrarProfesor(borrar);
			System.out.println("Profesor borrado.");
		} catch (Exception e) {
			System.out.println(ERROR + e.getMessage());
			System.out.println("No se pudo borrar el profesor.");
		}
	}

	/**
	 * Ejecuta la orden buscarProfesor de Opcion llamando al m�todo correspondiente
	 * de ModeloReservasAulas
	 */
	public void buscarProfesor() {
		Consola.mostrarCabecera("BUSCAR PROFESOR");
		Profesor buscado = null;
		try {
			buscado = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
		} catch (Exception e) {
			System.out.println(ERROR + e.getMessage());
		}
		Profesor encontrado = controlador.buscarProfesor(buscado);
		if (encontrado == null)
			System.out.println("El profesor buscado no existe.");
		else
			System.out.println("Se ha encontrado el profesor buscado: " + buscado);
	}

	/**
	 * Ejecuta la orden listarProfesores de Opcion llamando al m�todo
	 * correspondiente de ModeloReservasAulas
	 */
	public void listarProfesores() {
		Consola.mostrarCabecera("LISTAR PROFESORES");
		List<String> profesores = controlador.representarProfesores();
		if (profesores.size() == 0)
			System.out.println("No hay ningún profesor guardado.");
		for (String s : profesores)
			System.out.println(s);
	}

	/**
	 * Ejecuta la orden realizarReserva de Opcion llamando al m�todo correspondiente
	 * de ModeloReservasAulas
	 */
	public void realizarReserva() {
		Consola.mostrarCabecera("REALIZAR RESERVA");
		Profesor profesor = null;
		try {
			profesor = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
		} catch (Exception e) {
		}
		boolean lecturaCorrecta = true;
		if (controlador.buscarProfesor(profesor) == null) {
			System.out.println(ERROR + "El profesor introducido no existe.");
			lecturaCorrecta = false;
		}
		Reserva reserva = null;
		if (lecturaCorrecta) {
			try {
				reserva = leerReserva(profesor);
			} catch (Exception e) {
				System.out.println(ERROR + e.getMessage());
			}
		}
		try {
			controlador.realizarReserva(reserva);
			System.out.println("Reserva realizada correctamente.");
		} catch (Exception e) {
			System.out.println(ERROR + e.getMessage());
			System.out.println("La reserva no se pudo realizar.");
		}

	}

	/**
	 * M�todo privado que se encarga de leer una reserva
	 *
	 * @param profesor
	 *            el profesor responsable de la reserva
	 * @return null si el profesor o el aula no existen, la reserva le�da si existen
	 *         ambos
	 */
	private Reserva leerReserva(Profesor profesor) {
		Profesor buscado = controlador.buscarProfesor(profesor);
		if (buscado == null)
			return null;
		Aula buscada = controlador.buscarAula(new Aula(Consola.leerNombreAula(), 10));
		if (buscada == null) {
			System.out.println(ERROR + "El aula introducida no existe.");
			return null;
		}
		Reserva leida = null;
		try {
			int permanencia = Consola.elegirPermanencia();
			if (permanencia == 0)
				leida = new Reserva(buscado, buscada, new PermanenciaPorHora(Consola.leerDia(), Consola.leerHora()));
			if (permanencia == 1)
				leida = new Reserva(buscado, buscada, new PermanenciaPorTramo(Consola.leerDia(), Consola.leerTramo()));
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
		return leida;
	}

	/**
	 * Ejecuta la orden anularReserva de Opcion llamando al m�todo correspondiente
	 * de ModeloReservasAulas
	 */
	public void anularReserva() {
		Consola.mostrarCabecera("ANULAR RESERVA");
		Profesor buscado = null;
		try {
			buscado = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
		} catch (Exception e) {
			System.out.println(ERROR + e.getMessage());
		}
		boolean lecturaCorrecta = true;
		if (controlador.buscarProfesor(buscado) == null) {
			System.out.println("El profesor introducido no existe.");
			lecturaCorrecta = false;
		}
		Reserva reserva = null;
		if (lecturaCorrecta) {
			try {
				reserva = leerReserva(buscado);
			} catch (Exception e) {
				System.out.println(ERROR + e.getMessage());
			}
		}
		if (reserva == null)
			System.out.println("La reserva no se pudo anular.");
		else {
			try {
				controlador.anularReserva(reserva);
				System.out.println("Reserva anulada correctamente.");
			} catch (OperationNotSupportedException e) {
				System.out.println(ERROR + e.getMessage());
				System.out.println("La reserva no se pudo anular.");
			}
		}
	}

	/**
	 * Ejecuta la orden listarReservas de Opcion llamando al m�todo correspondiente
	 * de ModeloReservasAulas
	 */
	public void listarReservas() {
		Consola.mostrarCabecera("LISTAR RESERVAS");
		List<String> reservas = controlador.representarReservas();
		if (reservas.size() == 0)
			System.out.println("No hay ninguna reserva hecha.");
		for (String s : reservas)
			System.out.println(s);
	}

	/**
	 * Ejecuta la orden listarReservasAula de Opcion llamando al m�todo
	 * correspondiente de ModeloReservasAulas
	 */
	public void listarReservasAula() {
		Consola.mostrarCabecera("LISTAR RESERVAS AULA");
		Aula aula = null;
		try {
			aula = new Aula(Consola.leerNombreAula(), 10);
		} catch (Exception e) {
		}
		boolean lecturaCorrecta = true;
		try {
			if (controlador.buscarAula(aula) == null) {
				System.out.println(ERROR + "El aula introducida no existe.");
				lecturaCorrecta = false;
			}
		} catch (Exception e) {
			System.out.println(ERROR + e.getMessage());
			lecturaCorrecta = false;
		}
		List<Reserva> reservas = null;
		try {
			reservas = controlador.getReservasAula(aula);
		} catch (Exception e) {
			System.out.println(ERROR + e.getMessage());
			lecturaCorrecta = false;
		}
		if (lecturaCorrecta && reservas.size() == 0)
			System.out.println("El aula indicada no está reservada.");
		if (lecturaCorrecta) {
			for (Reserva r : reservas)
				System.out.println(r);
		}
	}

	/**
	 * Ejecuta la orden listarReservasProfesor de Opcion llamando al m�todo
	 * correspondiente de ModeloReservasAulas
	 */
	public void listarReservasProfesor() {
		Consola.mostrarCabecera("LISTAR RESERVAS PROFESOR");
		Profesor profesor = null;
		try {
			profesor = new Profesor(Consola.leerNombreProfesor(), CORREO_VALIDO);
		} catch (Exception e) {
			System.out.println(ERROR + e.getMessage());
		}
		boolean lecturaCorrecta = true;
		try {
			if (controlador.buscarProfesor(profesor) == null) {
				System.out.println(ERROR + "El profesor introducido no existe.");
				lecturaCorrecta = false;
			}
		} catch (Exception e) {
			System.out.println(ERROR + e.getMessage());
		}
		List<Reserva> reservas = null;
		try {
			reservas = controlador.getReservasProfesor(profesor);
		} catch (Exception e) {
			System.out.println(ERROR + e.getMessage());
			lecturaCorrecta = false;
		}
		if (lecturaCorrecta && reservas.size() == 0) {
			System.out.println("El profesor indicado no tiene ningún aula reservada.");
			lecturaCorrecta = false;
		}
		if (lecturaCorrecta) {
			for (Reserva r : reservas)
				System.out.println(r);
		}
	}

	/**
	 * Ejecuta la orden listarReservasPermanencia de Opcion llamando al m�todo
	 * correspondiente de ModeloReservasAulas
	 */
	public void listarReservasPermanencia() {
		Consola.mostrarCabecera("LISTAR RESERVAS PERMANENCIA");
		Permanencia permanencia = null;
		// Leemos la permanencia que queremos consultar.
		switch (Consola.elegirPermanencia()) {
		case 0:
			try {
				permanencia = new PermanenciaPorHora(Consola.leerDia(), Consola.leerHora());
			} catch (Exception e) {
				System.out.println(ERROR + e.getMessage());
			}
			break;
		case 1:
			try {
				permanencia = new PermanenciaPorTramo(Consola.leerDia(), Consola.leerTramo());
			} catch (Exception e) {
				System.out.println(ERROR + e.getMessage());
			}
		}
		List<Reserva> reservasPermanencia = null;
		try {
			reservasPermanencia = controlador.getReservasPermanencia(permanencia);
		} catch (Exception e) {
			System.out.println(ERROR + e.getMessage());
		}

		// Si no ha saltado error al obtener las reservas por permanencia
		if (reservasPermanencia != null) {
			if (reservasPermanencia.size() == 0)
				System.out.println("No hay reservas para la permanencia especificada.");
			if (reservasPermanencia.size() != 0) {
				System.out.println("Reservas correspondientes a la permanencia especificada:");
				for (Reserva r : reservasPermanencia)
					System.out.println(r);
			}
		}
	}

	/**
	 * Ejecuta la orden consultarDisponibilidad de Opcion llamando al m�todo
	 * correspondiente de ModeloReservasAulas
	 */
	public void consultarDisponibilidad() {
		Consola.mostrarCabecera("CONSULTAR DISPONIBILIDAD");
		Aula aula = null;
		Permanencia permanencia = null;
		boolean lecturaCorrecta = true;
		// Leemos el aula
		try {
			aula = new Aula(Consola.leerNombreAula(), 10);
		} catch (Exception e) {
			System.out.println(ERROR + e.getMessage());
			lecturaCorrecta = false;
		}
		// Comprobamos que el aula existe
		Aula aulaOtra = controlador.buscarAula(aula);
		if (aulaOtra == null) {
			System.out.println(ERROR + "El aula introducida no existe.");
			lecturaCorrecta = false;
		}
		if (lecturaCorrecta) {
			// Leemos la permanencia
			switch (Consola.elegirPermanencia()) {
			case 0:
				try {
					permanencia = new PermanenciaPorHora(Consola.leerDia(), Consola.leerHora());
				} catch (Exception e) {
					System.out.println(ERROR + e.getMessage());
					lecturaCorrecta = false;
				}
				break;
			case 1:
				try {
					permanencia = new PermanenciaPorTramo(Consola.leerDia(), Consola.leerTramo());
				} catch (Exception e) {
					System.out.println(ERROR + e.getMessage());
					lecturaCorrecta = false;
				}
			}
		}

		// Consultamos al controlador
		if (aulaOtra != null) {
			try {
				if (controlador.consultarDisponibilidad(aulaOtra, permanencia))
					System.out.println("El aula consultada está disponible para esa permanencia.");
				else
					System.out.println("El aula consultada no está disponible para esa permanencia.");
			} catch (Exception e) {
				System.out.println(ERROR + e.getMessage());
			}
		}
	}
}
