package org.iesalandalus.programacion.reservasaulas.vista.iugrafica.controladoresvistas;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.vista.iugrafica.utilidades.Dialogos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Clase controladora del fxml de la VentanaPrincipal
 *
 * @author Juan Antonio Manzano Plaza
 * @version 5
 *
 */
public class ControladorVentanaPrincipal implements Initializable{

	private IControladorReservasAulas controladorMVC;

	//Pestaña Aulas
	//TableView y columnas que listan las aulas y su menú contextual
	@FXML private TableView<Aula> tvAulas;
	@FXML private TableColumn<Aula, String> tcNombreAulas;
	@FXML private TableColumn<Aula, String> tcPuestosAulas;
	@FXML private ContextMenu cmAulas;
	@FXML private MenuItem miInsertarAula;
	@FXML private MenuItem miReservarAula;
	@FXML private MenuItem miBorrarAula;
	//TableView y columnas que listan las reservas de un aula seleccionada y su menú contextual
	@FXML private TableView<Reserva> tvReservasAulas;
	@FXML private TableColumn<Reserva, String> tcProfesorAulas;
	@FXML private TableColumn<Reserva, String> tcDiaAulas;
	@FXML private TableColumn<Reserva, String> tcHoraTramoAulas;
	@FXML private TableColumn<Reserva, String> tcPuntosAulas;
	@FXML private ContextMenu cmReservasAula;
	@FXML private MenuItem miAnularReservaAula;

	//Pestaña Profesores
	//TableView y columnas que listan los profesores y su menú contextual
	@FXML private TableView<Profesor> tvProfesores;
	@FXML private TableColumn<Profesor, String> tcNombreProfesores;
	@FXML private TableColumn<Profesor, String> tcCorreoProfesores;
	@FXML private TableColumn<Profesor, String> tcTelefonoProfesores;
	@FXML private ContextMenu cmProfesores;
	@FXML private MenuItem miInsertarProfesor;
	@FXML private MenuItem miProfesorReserva;
	@FXML private MenuItem miBorrarProfesor;
	//TableView y columnas que listan las reservas de un profesor seleccionado y su menú contextual
	@FXML private TableView<Reserva> tvReservasProfesores;
	@FXML private TableColumn<Reserva, String> tcAulaProfesores;
	@FXML private TableColumn<Reserva, String> tcDiaProfesores;
	@FXML private TableColumn<Reserva, String> tcHoraTramoProfesores;
	@FXML private TableColumn<Reserva, String> tcPuntosProfesores;
	@FXML private ContextMenu cmReservasProfesor;
	@FXML private MenuItem miAnularReservaProfesor;

	//Pestaña Reservas
	//TableView y columnas que listan las reservas y su menú contextual
	@FXML private TableView<Reserva> tvReservas;
	@FXML private TableColumn<Reserva, String> tcAulaReservas;
	@FXML private TableColumn<Reserva, String> tcProfesorReservas;
	@FXML private TableColumn<Reserva, String> tcDiaReservas;
	@FXML private TableColumn<Reserva, String> tcHoraTramoReservas;
	@FXML private TableColumn<Reserva, String> tcPuntosReservas;
	@FXML private ContextMenu cmReservas;
	@FXML private MenuItem miRealizarReserva;
	@FXML private MenuItem miAnularReservaReservas;

	private ObservableList<Aula> aulas = FXCollections.observableArrayList();
	private ObservableList<Profesor> profesores = FXCollections.observableArrayList();
	private ObservableList<Reserva> reservas = FXCollections.observableArrayList();
	private ObservableList<Reserva> reservasAula = FXCollections.observableArrayList();
	private ObservableList<Reserva> reservasProfesor = FXCollections.observableArrayList();

	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

	/**
	 * Método initialize de la interfaz Initializable
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setAtributosTablasAula();
		setAtributosTablasProfesor();
		setAtributosTablasReservas();
		setListeners();
	}

	/**
	 * Método que vincula las TableColumn de las TableView de la pestaña de las aulas con los atributos correspondientes de las Aulas y sus Reservas
	 */
	private void setAtributosTablasAula() {
		tcNombreAulas.setCellValueFactory(Aula -> new SimpleStringProperty(Aula.getValue().getNombre()));
		tcPuestosAulas.setCellValueFactory(Aula -> new SimpleStringProperty(Integer.toString(Aula.getValue().getPuestos())));
		tcProfesorAulas.setCellValueFactory(Reserva -> new SimpleStringProperty(Reserva.getValue().getProfesor().getNombre()));
		tcDiaAulas.setCellValueFactory(Reserva -> new SimpleStringProperty(FORMATO_DIA.format(Reserva.getValue().getPermanencia().getDia())));
		tcHoraTramoAulas.setCellValueFactory(Reserva -> new SimpleStringProperty(getPermanenciaString(Reserva.getValue())));
		tcPuntosAulas.setCellValueFactory(Reserva -> new SimpleStringProperty(Float.toString(Reserva.getValue().getPuntos())));
	}

	/**
	 * Método que vincula las TableColumn de las TableView de la pestaña de los profesores con los atributos correspondientes de los Profesores y sus Reservas
	 */
	private void setAtributosTablasProfesor() {
		tcNombreProfesores.setCellValueFactory(Profesor -> new SimpleStringProperty(Profesor.getValue().getNombre()));
		tcCorreoProfesores.setCellValueFactory(Profesor -> new SimpleStringProperty(Profesor.getValue().getCorreo()));
		tcTelefonoProfesores.setCellValueFactory(Profesor -> new SimpleStringProperty(Profesor.getValue().getTelefono()));
		tcAulaProfesores.setCellValueFactory(Reserva -> new SimpleStringProperty(Reserva.getValue().getAula().getNombre()));
		tcDiaProfesores.setCellValueFactory(Reserva -> new SimpleStringProperty(FORMATO_DIA.format(Reserva.getValue().getPermanencia().getDia())));
		tcHoraTramoProfesores.setCellValueFactory(Reserva -> new SimpleStringProperty(getPermanenciaString(Reserva.getValue())));
		tcPuntosProfesores.setCellValueFactory(Reserva -> new SimpleStringProperty(Float.toString(Reserva.getValue().getPuntos())));
	}

	/**
	 * Método que vincula las TableColumn de la TableView de la pestaña de las reservas con los atributos correspondientes de las Reservas
	 */
	private void setAtributosTablasReservas() {
		tcAulaReservas.setCellValueFactory(Reserva -> new SimpleStringProperty(Reserva.getValue().getAula().getNombre()));
		tcProfesorReservas.setCellValueFactory(Reserva -> new SimpleStringProperty(Reserva.getValue().getProfesor().getNombre()));
		tcDiaReservas.setCellValueFactory(Reserva -> new SimpleStringProperty(FORMATO_DIA.format(Reserva.getValue().getPermanencia().getDia())));
		tcHoraTramoReservas.setCellValueFactory(Reserva -> new SimpleStringProperty(getPermanenciaString(Reserva.getValue())));
		tcPuntosReservas.setCellValueFactory(Reserva -> new SimpleStringProperty(Float.toString(Reserva.getValue().getPuntos())));
	}

	/**
	 * Método que crea los Listeners para las TableView de Aulas y de Profesores
	 */
	private void setListeners() {
		tvAulas.getSelectionModel().selectedItemProperty().addListener((Ob, valorAntiguo, valorNuevo) -> {
			reservasAula.setAll(controladorMVC.getReservasAula(valorNuevo));
			tvReservasAulas.setItems(reservasAula);
		});

		tvProfesores.getSelectionModel().selectedItemProperty().addListener((Ob, valorAntiguo, valorNuevo) -> {
			reservasProfesor.setAll(controladorMVC.getReservasProfesor(valorNuevo));
			tvReservasProfesores.setItems(reservasProfesor);
		});
	}

	/**
	 * Método que obtiene el String que representa el Tramo o la hora dependiendo del tipo de Permanencia de la Reserva
	 *
	 * @param reserva la Reserva de la que queremos obtener el String de la hora o Tramo
	 * @return
	 */
	private String getPermanenciaString(Reserva reserva) {
		Permanencia permanencia = reserva.getPermanencia();
		if(permanencia instanceof PermanenciaPorTramo)
			return ((PermanenciaPorTramo) permanencia).getTramo().toString();
		else
			return ((PermanenciaPorHora) permanencia).getHora().format(FORMATO_HORA);
	}

	/**
	 * Método que establece los valores de la TableView de Aulas obtenidas desde el controlador del programa
	 */
	public void setAulas() {
		aulas.setAll(controladorMVC.getAulas());
		tvAulas.setItems(aulas);
	}

	/**
	 * Método que establece los valores de la TableView de Profesores obtenidos desde el controlador del programa
	 */
	public void setProfesores() {
		profesores.setAll(controladorMVC.getProfesores());
		tvProfesores.setItems(profesores);
	}

	/**
	 * Método que establece los valores de la TableView de Reservas obtenidas desde el controlador del programa
	 */
	public void setReservas() {
		reservas.setAll(controladorMVC.getReservas());
		tvReservas.setItems(reservas);
	}

	/**
	 * Método que guarda el controlador del modelo vista controlador en una variable de clase
	 *
	 * @param controlador el controlador del programa
	 */
	public void setControladorMVC(IControladorReservasAulas controlador) {
		controladorMVC = controlador;
	}

	/**
	 * Método que gestiona el evento de pulsar sobre el botón del menú contextual de insertar aula
	 *
	 * @param event el evento a gestionar
	 */
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
			//tvAulas.getSelectionModel().selectedItemProperty().addListener((ob, oldValue, newValue) -> filaSeleccionada(tvAulas.getSelectionModel().getSelectedItem()));
			escenario.showAndWait();
		} catch (IOException e) {
			Logger.getLogger(ControladorVentanaPrincipal.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	/**
	 * Método que gestiona el evento de pulsar sobre el botón del menú contextual de insertar profesor
	 *
	 * @param event el evento a gestionar
	 */
	@FXML private void insertarProfesor(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../vistas/InsertarProfesor.fxml"));
			Parent raiz = loader.load();
			ControladorInsertarProfesor controlador = loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setProfesores(profesores);
			Scene escena = new Scene(raiz);
			Stage escenario = new Stage();
			escenario.initModality(Modality.APPLICATION_MODAL);
			escenario.setScene(escena);
			escenario.setTitle("Insertar Profesor");
			escenario.showAndWait();
		} catch (IOException e) {
			Logger.getLogger(ControladorVentanaPrincipal.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	/**
	 * Método que gestiona el evento de pulsar sobre el botón del menú contextual de realizar reserva
	 *
	 * @param event
	 */
	@FXML private void insertarReserva(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../vistas/InsertarReserva.fxml"));
			Parent raiz = loader.load();
			ControladorInsertarReserva controlador = loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setReservas(reservas);
			Scene escena = new Scene(raiz);
			Stage escenario = new Stage();
			escenario.initModality(Modality.APPLICATION_MODAL);
			escenario.setScene(escena);
			escenario.setTitle("Insertar reserva");
			escenario.showAndWait();
		} catch (IOException e) {
			Logger.getLogger(ControladorVentanaPrincipal.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	/**
	 * Método que gestiona el evento de pulsar sobre el botón del menú contextual de borrar aula
	 *
	 * @param event el evento a gestionar
	 */
	@FXML private void borrarAula(ActionEvent event) {
		Aula aula = null;
		try {
			aula = tvAulas.getSelectionModel().getSelectedItem();
			if(aula!=null && Dialogos.mostrarDialogoConfirmaion("Borrar", "¿Estás seguro de que quieres borrar el aula?", null)) {
				controladorMVC.borrarAula(aula);
				aulas.remove(aula);
				Dialogos.mostrarDialogoInformacion("Borrar aula", "Aula borrada satisfactoriamente", null);
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Borrar aula", e.getMessage());
		}
	}

	/**
	 * Método que gestiona el evento de pulsar sobre el botón del menú contextual de borrar profesor
	 *
	 * @param event
	 */
	@FXML private void borrarProfesor(ActionEvent event) {
		Profesor profesor = null;
		try {
			profesor = tvProfesores.getSelectionModel().getSelectedItem();
			if(profesor!=null && Dialogos.mostrarDialogoConfirmaion("Borrar", "¿Estás seguro de que quieres borrar el profesor?", null)) {
				controladorMVC.borrarProfesor(profesor);
				profesores.remove(profesor);
				Dialogos.mostrarDialogoInformacion("Borrar profesor", "Profesor borrado satisfactoriamente", null);
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Borrar profesor", e.getMessage());
		}
	}

	/**
	 * Método que gestiona el evento de pulsar sobre el botón del menú contextual de anular reserva
	 *
	 * @param event el evento a gestionar
	 */
	@FXML private void anularReserva(ActionEvent event) {
		Reserva reserva = null;
		try {
			reserva = tvReservas.getSelectionModel().getSelectedItem();
			if(reserva!=null && Dialogos.mostrarDialogoConfirmaion("Anular reserva", "¿Estás seguro de que quieres anular la reserva?", null)) {
				controladorMVC.anularReserva(reserva);
				reservas.remove(reserva);
				Dialogos.mostrarDialogoInformacion("Anular reserva", "Reserva anulada satisfactoriamente", null);
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Anular reserva", e.getMessage());
		}
	}

	/**
	 * Método que gestiona el evento de pulsar sobre el botón del menú contextual de anular reserva de la tabla de Aulas
	 *
	 * @param event el evento a gestionar
	 */
	@FXML private void anularReservaAula(ActionEvent event) {
		Reserva reserva = null;
		try {
			reserva = tvReservasAulas.getSelectionModel().getSelectedItem();
			if(reserva!=null && Dialogos.mostrarDialogoConfirmaion("Anular reserva", "¿Estás seguro de que quieres anular la reserva?", null)) {
				controladorMVC.anularReserva(reserva);
				reservas.remove(reserva);
				Dialogos.mostrarDialogoInformacion("Anular reserva", "Reserva anulada satisfactoriamente", null);
				reservasAula.setAll(controladorMVC.getReservasAula(tvAulas.getSelectionModel().getSelectedItem()));
				tvReservasProfesores.setItems(reservasAula);
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Anular reserva", e.getMessage());
		}
	}

	/**
	 * Método que gestiona el evento de pulsar sobre el botón del menú contextual de anular reserva de la tabla de Profesores
	 *
	 * @param event el evento a gestionar
	 */
	@FXML private void anularReservaProfesor(ActionEvent event) {
		Reserva reserva = null;
		try {
			reserva = tvReservasProfesores.getSelectionModel().getSelectedItem();
			if(reserva!=null && Dialogos.mostrarDialogoConfirmaion("Anular reserva", "¿Estás seguro de que quieres anular la reserva?", null)) {
				controladorMVC.anularReserva(reserva);
				reservas.remove(reserva);
				Dialogos.mostrarDialogoInformacion("Anular reserva", "Reserva anulada satisfactoriamente", null);
				reservasProfesor.setAll(controladorMVC.getReservasProfesor(tvProfesores.getSelectionModel().getSelectedItem()));
				tvReservasAulas.setItems(reservasProfesor);
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Anular reserva", e.getMessage());
		}
	}

	/**
	 * Método que gestiona el evento de pulsar sobre el botón del menú contextual de reservar aula
	 *
	 * @param event el evento a gestionar
	 */
	@FXML private void reservarAula(ActionEvent event) {
		Aula aula = null;
		try {
			aula = tvAulas.getSelectionModel().getSelectedItem();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../vistas/InsertarReserva.fxml"));
			Parent raiz = loader.load();
			ControladorInsertarReserva controlador = loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setReservas(reservas);
			if(aula!=null)
				controlador.setAula(aula);
			Scene escena = new Scene(raiz);
			Stage escenario = new Stage();
			escenario.initModality(Modality.APPLICATION_MODAL);
			escenario.setScene(escena);
			escenario.setTitle("Insertar reserva");
			escenario.showAndWait();
			reservas.setAll(controladorMVC.getReservas());
			tvReservas.setItems(reservas);
		} catch (IOException e) {
			Logger.getLogger(ControladorVentanaPrincipal.class.getName()).log(Level.SEVERE, null, e);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Reservar aula", e.getMessage());
		}
	}

	/**
	 * Método que gestiona el evento de pulsar sobre el botón del menú contextual de realizar una reserva desde un profesor
	 *
	 * @param event el evento a gestionar
	 */
	@FXML private void profesorReserva(ActionEvent event) {
		Profesor profesor = null;
		try {
			profesor = tvProfesores.getSelectionModel().getSelectedItem();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../vistas/InsertarReserva.fxml"));
			Parent raiz = loader.load();
			ControladorInsertarReserva controlador = loader.getController();
			controlador.setControladorMVC(controladorMVC);
			controlador.setReservas(reservas);
			if(profesor!=null)
				controlador.setProfesor(profesor);
			Scene escena = new Scene(raiz);
			Stage escenario = new Stage();
			escenario.initModality(Modality.APPLICATION_MODAL);
			escenario.setScene(escena);
			escenario.setTitle("Insertar reserva");
			escenario.showAndWait();
			reservas.setAll(controladorMVC.getReservas());
			tvReservas.setItems(reservas);
		} catch (IOException e) {
			Logger.getLogger(ControladorVentanaPrincipal.class.getName()).log(Level.SEVERE, null, e);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Profesor reserva", e.getMessage());
		}
	}
}
