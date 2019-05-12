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

public class VistaReservasAulas extends Application implements IVistaReservasAulas{

	private IControladorReservasAulas controladorMVC;
	private static VistaReservasAulas instancia = null;

	public VistaReservasAulas() {
		if(instancia != null)
			controladorMVC = instancia.controladorMVC;
		else
			instancia = this;
	}

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

	private void confirmarSalida(Stage escenarioPrincipal, WindowEvent e) {
		if(Dialogos.mostrarDialogoConfirmaion("Salir", "¿Estás seguro de que quieres salir de la aplicación?", escenarioPrincipal)){
			this.controladorMVC.salir();
			escenarioPrincipal.close();
		} else
			e.consume();
	}

	public IControladorReservasAulas getControladorMVC() {
		return controladorMVC;
	}

	@Override
	public void setControlador(IControladorReservasAulas controlador) {
		this.controladorMVC = controlador;
	}

	@Override
	public void comenzar() {
		launch(this.getClass());
	}

	@Override
	public void salir() {
		this.controladorMVC.salir();
	}

	@Override
	public void insertarAula() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	@Override
	public void borrarAula() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	@Override
	public void buscarAula() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	@Override
	public void listarAulas() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	@Override
	public void insertarProfesor() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	@Override
	public void borrarProfesor() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	@Override
	public void buscarProfesor() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	@Override
	public void listarProfesores() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	@Override
	public void realizarReserva() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	@Override
	public void anularReserva() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	@Override
	public void listarReservas() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	@Override
	public void listarReservasAula() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	@Override
	public void listarReservasProfesor() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	@Override
	public void listarReservasPermanencia() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

	@Override
	public void consultarDisponibilidad() {
		throw new UnsupportedOperationException("Método no implementado.");
	}

}
