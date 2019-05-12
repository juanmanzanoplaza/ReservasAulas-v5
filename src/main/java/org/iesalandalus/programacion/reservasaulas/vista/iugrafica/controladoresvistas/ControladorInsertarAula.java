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

public class ControladorInsertarAula implements Initializable {

	private static final String ER_PUESTOS = "^[0-9]*$";

	private IControladorReservasAulas controladorMVC;

	private ObservableList<Aula> aulas;

	@FXML private TextField tfNombre;
	@FXML private TextField tfPuestos;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setControladorMVC(IControladorReservasAulas controlador) {
		controladorMVC = controlador;
	}

	public void setAulas(ObservableList<Aula> aulas) {
		this.aulas = aulas;
	}

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

	@FXML private void cancelar(ActionEvent event) {
		Stage escenario = (Stage)((Node) event.getSource()).getScene().getWindow();

		if(Dialogos.mostrarDialogoConfirmaion("Salir", "¿Estás seguro de que no quieres insertar un aula?", escenario))
			escenario.close();
		else
			event.consume();
	}

	@FXML private void comprobarPuestos(ActionEvent event) {
		Matcher m = Pattern.compile(ER_PUESTOS).matcher(tfPuestos.getText());
		if(!m.matches()) {
			String nuevoValor = tfPuestos.getText();
			tfPuestos.setText(nuevoValor.substring(0, nuevoValor.length()-1));
		}
	}

}
