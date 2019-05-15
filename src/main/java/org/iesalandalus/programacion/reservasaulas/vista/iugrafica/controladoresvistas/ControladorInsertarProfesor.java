package org.iesalandalus.programacion.reservasaulas.vista.iugrafica.controladoresvistas;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.vista.iugrafica.utilidades.Dialogos;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador del fichero fxml InsertarProfesor
 * 
 * @author Juan Antonio Manzano Plaza
 * @version 4
 *
 */
public class ControladorInsertarProfesor implements Initializable {

	private IControladorReservasAulas controladorMVC;

	private ObservableList<Profesor> profesores;

	@FXML private TextField tfNombre;
	@FXML private TextField tfCorreo;
	@FXML private TextField tfTelefono;

	/**
	 * Método initialize de la interfaz Initializable
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	/**
	 * Método set para el controlador del programa
	 * 
	 * @param controlador el controlador del modelo vista controlador
	 */
	public void setControladorMVC(IControladorReservasAulas controlador) {
		controladorMVC = controlador;
	}

	/**
	 * Método set para la colección de profesores
	 * 
	 * @param profesores la colección de profesores proveniente de la ventana principal
	 */
	public void setProfesores(ObservableList<Profesor> profesores) {
		this.profesores = profesores;
	}

	/**
	 * Método que gestiona el evento de haber pulsado el botón aceptar
	 * 
	 * @param event el evento que gestiona
	 */
	@FXML private void aceptar(ActionEvent event) {
		Profesor profesor = null;

		try {
			Stage escenario = (Stage)((Node) event.getSource()).getScene().getWindow();
			if(tfTelefono.getText().equals(""))
				profesor = new Profesor(tfNombre.getText(), tfCorreo.getText());
			else
				profesor = new Profesor(tfNombre.getText(), tfCorreo.getText(), tfTelefono.getText());
			controladorMVC.insertarProfesor(profesor);
			profesores.add(profesor);
			Dialogos.mostrarDialogoInformacion("Nuevo Profesor", "Profesor insertado correctamente");
			escenario.close();
		} catch (IllegalArgumentException | OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Profesor", e.getMessage());
		}
	}

	/**
	 * Método que gestiona el evento de haber pulsado el botón cancelar
	 * 
	 * @param event el evento que gestiona
	 */
	@FXML private void cancelar(ActionEvent event) {
		Stage escenario = (Stage)((Node) event.getSource()).getScene().getWindow();

		if(Dialogos.mostrarDialogoConfirmaion("Salir", "¿Estás seguro de que no quieres insertar un profesor?", escenario))
			escenario.close();
		else
			event.consume();
	}

}
