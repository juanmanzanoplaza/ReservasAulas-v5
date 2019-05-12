package org.iesalandalus.programacion.reservasaulas.vista.iugrafica.controladoresvistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorVentanaPrincipal implements Initializable{

	private IControladorReservasAulas controladorMVC;

	//Pestaña Aulas
	//TableView y columnas que listan las aulas y su menú contextual
	@FXML private TableView<Aula> tvAulas;
	@FXML private TableColumn<Aula, String> tcNombreAulas;
	@FXML private TableColumn<Aula, Integer> tcPuestosAulas;
	//@FXML private ContextMenu cmAulas;
	//TableView y columnas que listan las reservas de un aula seleccionada y su menú contextual
	@FXML private TableView<Reserva> tvReservasAulas;
	@FXML private TableColumn<Profesor, String> tcProfesorAulas;
	@FXML private TableColumn<Permanencia, String> tcDiaAulas;
	@FXML private TableColumn<Permanencia, String> tcHoraAulas;
	@FXML private TableColumn<Permanencia, String> tcTramoAulas;
	//@FXML private ContextMenu cmReservasAula;

	//Pestaña Profesores
	//TableView y columnas que listan los profesores y su menú contextual
	@FXML private TableView<Profesor> tvProfesores;
	@FXML private TableColumn<Profesor, String> tcNombreProfesores;
	@FXML private TableColumn<Profesor, String> tcCorreoProfesores;
	@FXML private TableColumn<Profesor, String> tcTelefonoProfesores;
	//@FXML private ContextMenu cmProfesores;
	//TableView y columnas que listan las reservas de un profesor seleccionado y su menú contextual
	@FXML private TableView<Reserva> tvReservasProfesores;
	@FXML private TableColumn<Aula, String> tcAulaProfesores;
	@FXML private TableColumn<Permanencia, String> tcDiaProfesores;
	@FXML private TableColumn<Permanencia, String> tcHoraProfesores;
	@FXML private TableColumn<Permanencia, String> tcTramoProfesores;
	//@FXML private ContextMenu cmReservasProfesor;

	//Pestaña Reservas
	//TableView y columnas que listan las reservas y su menú contextual
	@FXML private TableView<Reserva> tvReservas;
	@FXML private TableColumn<Aula, String> tcAulaReservas;
	@FXML private TableColumn<Profesor, String> tcProfesorReservas;
	@FXML private TableColumn<Permanencia, String> tcDiaReservas;
	@FXML private TableColumn<Permanencia, String> tcHoraReservas;
	@FXML private TableColumn<Permanencia,String> tcTramoReservas;
	//@FXML private ContextMenu cmReservas;

	private ObservableList<Aula> aulas = FXCollections.observableArrayList();
	private ObservableList<Profesor> profesores = FXCollections.observableArrayList();
	private ObservableList<Reserva> reservas = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setAtributosTablasAula();
		setAtributosTablasProfesor();
		setAtributosTablasReservas();
	}

	private void setAtributosTablasAula() {
		tcNombreAulas.setCellValueFactory(new PropertyValueFactory<Aula,String>("nombre"));
		tcPuestosAulas.setCellValueFactory(new PropertyValueFactory<Aula,Integer>("puestos"));
		tcProfesorAulas.setCellValueFactory(new PropertyValueFactory<Profesor,String>("nombre"));
		tcDiaAulas.setCellValueFactory(new PropertyValueFactory<Permanencia,String>("dia"));
		tcHoraAulas.setCellValueFactory(new PropertyValueFactory<Permanencia,String>("hora"));
		tcHoraAulas.setCellValueFactory(new PropertyValueFactory<Permanencia,String>("tramo"));


	}

	private void setAtributosTablasProfesor() {
		tcNombreProfesores.setCellValueFactory(new PropertyValueFactory<Profesor,String>("nombre"));
		tcCorreoProfesores.setCellValueFactory(new PropertyValueFactory<Profesor,String>("correo"));
		tcTelefonoProfesores.setCellValueFactory(new PropertyValueFactory<Profesor,String>("telefono"));
		tcAulaProfesores.setCellValueFactory(new PropertyValueFactory<Aula,String>("nombre"));
		tcDiaProfesores.setCellValueFactory(new PropertyValueFactory<Permanencia,String>("dia"));
		tcHoraProfesores.setCellValueFactory(new PropertyValueFactory<Permanencia,String>("hora"));
		tcTramoProfesores.setCellValueFactory(new PropertyValueFactory<Permanencia,String>("tramo"));
	}

	private void setAtributosTablasReservas() {
		tcAulaReservas.setCellValueFactory(new PropertyValueFactory<Aula,String>("nombre"));
		tcProfesorReservas.setCellValueFactory(new PropertyValueFactory<Profesor, String>("nombre"));
		tcDiaReservas.setCellValueFactory(new PropertyValueFactory<Permanencia,String>("dia"));
		tcHoraReservas.setCellValueFactory(new PropertyValueFactory<Permanencia,String>("hora"));
		tcTramoReservas.setCellValueFactory(new PropertyValueFactory<Permanencia,String>("tramo"));
	}

	public void setAulas() {
		aulas.setAll(controladorMVC.getAulas());
		tvAulas.setItems(aulas);
	}

	public void setProfesores() {
		profesores.setAll(controladorMVC.getProfesores());
		tvProfesores.setItems(profesores);
	}

	public void setReservas() {
		reservas.setAll(controladorMVC.getReservas());
		tvReservas.setItems(reservas);
	}

	public void setControladorMVC(IControladorReservasAulas controlador) {
		controladorMVC = controlador;
	}

	//Ahora hay que empezar a dar funcionalidad a todos los menús contextuales
	//FALTA -> Cambiar el fxml para hacer que cada uno de los menús llame al método correspondiente
	//FALTA -> Implementar los métodos

	@FXML private void insertarAula(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../vistas/InsertarAula.fxml"));
			Parent raiz = loader.load();
			ControladorInsertarAula controlador = loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setAulas(aulas);
			Scene escena = new Scene(raiz);
			Stage escenario = new Stage();
			escenario.initModality(Modality.APPLICATION_MODAL);
			escenario.setScene(escena);
			escenario.setTitle("Insertar Aula");
			escenario.showAndWait();
		} catch (IOException e) {
			Logger.getLogger(ControladorVentanaPrincipal.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@FXML private void insertarProfesor(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../vistas/InsertarProfesor.fxml"));
			Parent raiz = loader.load();
			ControladorInsertarProfesor controlador = loader.getController();

		} catch (IOException e) {
			Logger.getLogger(ControladorVentanaPrincipal.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
