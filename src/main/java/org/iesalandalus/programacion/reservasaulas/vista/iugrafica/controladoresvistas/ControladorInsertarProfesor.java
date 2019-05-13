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

public class ControladorInsertarProfesor implements Initializable {

	//private static final String ER_CORREO = "^[a-z0-9]+(\\.[a-z0-9]+)*@[a-z]+(\\.[a-z]+)*(\\.[a-z]{1,4})$";
	//private static final String ER_TELEFONO = "^[69][0-9]{8}$";

	private IControladorReservasAulas controladorMVC;

	private ObservableList<Profesor> profesores;

	@FXML private TextField tfNombre;
	@FXML private TextField tfCorreo;
	@FXML private TextField tfTelefono;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setControladorMVC(IControladorReservasAulas controlador) {
		controladorMVC = controlador;
	}

	public void setProfesores(ObservableList<Profesor> profesores) {
		this.profesores = profesores;
	}

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

	@FXML private void cancelar(ActionEvent event) {
		Stage escenario = (Stage)((Node) event.getSource()).getScene().getWindow();

		if(Dialogos.mostrarDialogoConfirmaion("Salir", "¿Estás seguro de que no quieres insertar un profesor?", escenario))
			escenario.close();
		else
			event.consume();
	}

	/**@FXML private void comprobarCorreo(ActionEvent event) {
		Matcher m = Pattern.compile(ER_CORREO).matcher(tfCorreo.getText());
		if(!m.matches()) {
			String nuevoValor = tfCorreo.getText();
			tfCorreo.setText(nuevoValor.substring(0, nuevoValor.length()-1));
		}
	}

	@FXML private void comprobarTelefono(ActionEvent event) {
		Matcher m = Pattern.compile(ER_TELEFONO).matcher(tfTelefono.getText());
		if(!m.matches()) {
			String nuevoValor = tfTelefono.getText();
			tfTelefono.setText(nuevoValor.substring(0, nuevoValor.length()-1));
		}
	}**/

}
