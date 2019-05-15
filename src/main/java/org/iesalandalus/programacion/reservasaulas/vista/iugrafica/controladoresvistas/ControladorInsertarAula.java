package org.iesalandalus.programacion.reservasaulas.vista.iugrafica.controladoresvistas;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.vista.iugrafica.utilidades.Dialogos;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador de la ventana de la interfaz gráfica InsertarAula.fxml
 * 
 * @author Juan Antonio Manzano Plaza
 * @version 4
 *
 */
public class ControladorInsertarAula implements Initializable {

	private IControladorReservasAulas controladorMVC;

	private ObservableList<Aula> aulas;

	@FXML private TextField tfNombre;
	@FXML private TextField tfPuestos;


	/**
	 * Método para initialize de la interfaz Initializable
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	/**
	 * Método set para el controlador del patrón Modelo Vista Controlador
	 * 
	 * @param controlador el controlador del programa
	 */
	public void setControladorMVC(IControladorReservasAulas controlador) {
		controladorMVC = controlador;
	}

	/**
	 * Método set para las aulas
	 * 
	 * @param aulas las aulas que obtiene desde la ventana principal
	 */
	public void setAulas(ObservableList<Aula> aulas) {
		this.aulas = aulas;
	}

	/**
	 * Método que gestiona el evento de haber pulsado el botón aceptar
	 * 
	 * @param event el evento que captura
	 */
	@FXML private void aceptar(ActionEvent event) {
		Aula aula = null;

		try {
			Stage escenario = (Stage)((Node) event.getSource()).getScene().getWindow();
			aula = new Aula(tfNombre.getText(), Integer.parseInt(tfPuestos.getText()));
			controladorMVC.insertarAula(aula);
			aulas.add(aula);
			Dialogos.mostrarDialogoInformacion("Nueva Aula", "Aula insertada correctamente");
			escenario.close();
		} catch(IllegalArgumentException | OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Aula", e.getMessage());
		}
	}

	/**
	 * Método que gestiona el evento de haber pulsado el botón cancelar
	 * 
	 * @param event el evento que captura
	 */
	@FXML private void cancelar(ActionEvent event) {
		Stage escenario = (Stage)((Node) event.getSource()).getScene().getWindow();

		if(Dialogos.mostrarDialogoConfirmaion("Salir", "¿Estás seguro de que no quieres insertar un aula?", escenario))
			escenario.close();
		else
			event.consume();
	}

}
