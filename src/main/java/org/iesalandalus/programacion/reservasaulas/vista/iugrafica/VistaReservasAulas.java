package org.iesalandalus.programacion.reservasaulas.vista.iugrafica;

import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;
import org.iesalandalus.programacion.reservasaulas.vista.IVistaReservasAulas;
import org.iesalandalus.programacion.reservasaulas.vista.iugrafica.controladoresvistas.ControladorVentanaPrincipal;
import org.iesalandalus.programacion.reservasaulas.vista.iugrafica.utilidades.Dialogos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Clase principal de la vista gráfica
 * 
 * @author Juan Antonio Manzano Plaza
 * @version 4
 *
 */
public class VistaReservasAulas extends Application implements IVistaReservasAulas{

	private IControladorReservasAulas controladorMVC;
	private static VistaReservasAulas instancia = null;

	/**
	 * Constructor de la clase
	 */
	public VistaReservasAulas() {
		if(instancia != null)
			controladorMVC = instancia.controladorMVC;
		else
			instancia = this;
	}

	/**
	 * Método start de la clase Application
	 */
	@Override
	public void start(Stage escenarioPrincipal) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("vistas/VentanaPrincipal.fxml"));
			Parent raiz = loader.load();
			ControladorVentanaPrincipal controlador=loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setAulas();
			controlador.setProfesores();
			controlador.setReservas();

			Scene escena = new Scene(raiz);
			escenarioPrincipal.setOnCloseRequest(e -> confirmarSalida(escenarioPrincipal, e));
			escenarioPrincipal.setTitle("Gestión de reservas para el instituto IES Al-Ándalus");
			escenarioPrincipal.setScene(escena);
			escenarioPrincipal.setResizable(false);
			escenarioPrincipal.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que lanza un diálogo de confirmación al pulsar sobre el botón cerrar de la ventana
	 * @param escenarioPrincipal la escena
	 * @param e el evento que debe capturar
	 */
	private void confirmarSalida(Stage escenarioPrincipal, WindowEvent e) {
		if(Dialogos.mostrarDialogoConfirmaion("Salir", "¿Estás seguro de que quieres salir de la aplicación?", escenarioPrincipal)){
			this.controladorMVC.salir();
			escenarioPrincipal.close();
		} else
			e.consume();
	}

	/**
	 * Método que devuelve el controlador del programa
	 * @return el controlador del modelo vista controlador
	 */
	public IControladorReservasAulas getControladorMVC() {
		return controladorMVC;
	}

	/**
	 * Método que establece el controlador del programa
	 * @param controlador el controlador del modelo vista controlador
	 */
	@Override
	public void setControlador(IControladorReservasAulas controlador) {
		this.controladorMVC = controlador;
	}

	/**
	 * Método que lanza el método start
	 */
	@Override
	public void comenzar() {
		launch(this.getClass());
	}

	/**
	 * Método que da al controlador la orden salir
	 */
	@Override
	public void salir() {
		this.controladorMVC.salir();
	}

	/**
	 * Método necesario para que implemente la interfaz de la vista
	 */
	@Override
	public void insertarAula() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	/**
	 * Método necesario para que implemente la interfaz de la vista
	 */
	@Override
	public void borrarAula() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	/**
	 * Método necesario para que implemente la interfaz de la vista
	 */
	@Override
	public void buscarAula() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	/**
	 * Método necesario para que implemente la interfaz de la vista
	 */
	@Override
	public void listarAulas() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	/**
	 * Método necesario para que implemente la interfaz de la vista
	 */
	@Override
	public void insertarProfesor() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	/**
	 * Método necesario para que implemente la interfaz de la vista
	 */
	@Override
	public void borrarProfesor() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	/**
	 * Método necesario para que implemente la interfaz de la vista
	 */
	@Override
	public void buscarProfesor() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	/**
	 * Método necesario para que implemente la interfaz de la vista
	 */
	@Override
	public void listarProfesores() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	/**
	 * Método necesario para que implemente la interfaz de la vista
	 */
	@Override
	public void realizarReserva() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	/**
	 * Método necesario para que implemente la interfaz de la vista
	 */
	@Override
	public void anularReserva() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	/**
	 * Método necesario para que implemente la interfaz de la vista
	 */
	@Override
	public void listarReservas() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	/**
	 * Método necesario para que implemente la interfaz de la vista
	 */
	@Override
	public void listarReservasAula() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	/**
	 * Método necesario para que implemente la interfaz de la vista
	 */
	@Override
	public void listarReservasProfesor() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	/**
	 * Método necesario para que implemente la interfaz de la vista
	 */
	@Override
	public void listarReservasPermanencia() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	/**
	 * Método necesario para que implemente la interfaz de la vista
	 */
	@Override
	public void consultarDisponibilidad() {
		throw new UnsupportedOperationException("Método no implementado.");
	}
}
