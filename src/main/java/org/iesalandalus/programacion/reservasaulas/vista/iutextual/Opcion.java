package org.iesalandalus.programacion.reservasaulas.vista.iutextual;

import org.iesalandalus.programacion.reservasaulas.vista.IVistaReservasAulas;

/**
 *
 * Representa las distintas opciones que ofrece el men� de la aplicaci�n.
 *
 * @see VistaReservasAulas
 * @author Juan Antonio Manzano Plaza
 * @version 3
 *
 */
public enum Opcion {
	SALIR("Salir.") {
		public void ejecutar() {
			vista.salir();
		}
	},
	INSERTAR_AULA("Insertar aula.") {
		public void ejecutar() {
			vista.insertarAula();
		}
	},
	BORRAR_AULA("Borrar aula.") {
		public void ejecutar() {
			vista.borrarAula();
		}
	},
	BUSCAR_AULA("Buscar aula.") {
		public void ejecutar() {
			vista.buscarAula();
		}
	},
	LISTAR_AULAS("Listar aulas.") {
		public void ejecutar() {
			vista.listarAulas();
		}
	},
	INSERTAR_PROFESOR("Insertar profesor.") {
		public void ejecutar() {
			vista.insertarProfesor();
		}
	},
	BORRAR_PROFESOR("Borrar profesor.") {
		public void ejecutar() {
			vista.borrarProfesor();
		}
	},
	BUSCAR_PROFESOR("Buscar profesor.") {
		public void ejecutar() {
			vista.buscarProfesor();
		}
	},
	LISTAR_PROFESORES("Listar profesores.") {
		public void ejecutar() {
			vista.listarProfesores();
		}
	},
	INSERTAR_RESERVA("Insertar reserva.") {
		public void ejecutar() {
			vista.realizarReserva();
		}
	},
	BORRAR_RESERVA("Borrar reserva.") {
		public void ejecutar() {
			vista.anularReserva();
		}
	},
	LISTAR_RESERVAS("Listar reservas.") {
		public void ejecutar() {
			vista.listarReservas();
		}
	},
	LISTAR_RESERVAS_AULA("Listar reservas por aula.") {
		public void ejecutar() {
			vista.listarReservasAula();
		}
	},
	LISTAR_RESERVAS_PROFESOR("Listar reservas por profesor.") {
		public void ejecutar() {
			vista.listarReservasProfesor();
		}
	},
	LISTAR_RESERVAS_PERMANENCIA("Listar reservas por permanencia.") {
		public void ejecutar() {
			vista.listarReservasPermanencia();
		}
	},
	CONSULTAR_DISPONIBILIDAD("Consultar disponibilidad.") {
		public void ejecutar() {
			vista.consultarDisponibilidad();
		}
	};

	private String mensajeAMostrar;
	private static IVistaReservasAulas vista;

	/**
	 * Constructor privado para evitar instanciar objetos de la clase.
	 *
	 * @param mensajeAMostrar
	 *            el t�tulo de la opci�n
	 */
	private Opcion(String mensajeAMostrar) {
		this.mensajeAMostrar = mensajeAMostrar;
	}

	/**
	 * M�todo get que devuelve el t�tulo de la opci�n
	 *
	 * @return el t�tulo de la opci�n
	 */
	public String getMensaje() {
		return mensajeAMostrar;
	}

	/**
	 * Llama al m�todo correspondiente a la opci�n de la clase VistaReservasAulas
	 */
	public abstract void ejecutar();

	/**
	 * M�todo set que inicializa la variable vista
	 *
	 * @param vist
	 *            objeto de la clase VistaReservasAulas sobre el que se van a
	 *            realizar las opciones
	 */
	protected static void setVista(IVistaReservasAulas vist) {
		vista = vist;
	}

	/**
	 * Define como debe mostrarse un objeto Opcion
	 *
	 * @return mensajeAMostrar el t�tulo de la opci�n
	 */
	public String toString() {
		return mensajeAMostrar;
	}

	/**
	 * Devuelve la opci�n correspondiente al valor recibido
	 *
	 * @param ordinal
	 *            el valor de la opci�n
	 * @return la opci�n correspondiente al ordinal
	 */
	public static Opcion getOpcionSegunOrdinal(int ordinal) {
		return Opcion.values()[ordinal];
	}

	/**
	 * Comprueba si un ordinal est� dentro del rango de valores de Opcion
	 *
	 * @param ordinal
	 *            el valor de la opci�n que se desea seleccionar
	 * @return True si el valor est� dentro del rango, False si no
	 */
	public static boolean esOrdinalValido(int ordinal) {
		if (ordinal >= 0 && ordinal < Opcion.values().length)
			return true;
		return false;
	}

}
