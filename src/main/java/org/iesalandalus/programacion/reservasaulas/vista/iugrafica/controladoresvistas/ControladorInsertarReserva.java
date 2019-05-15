package org.iesalandalus.programacion.reservasaulas.vista.iugrafica.controladoresvistas;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Observable;
import java.util.ResourceBundle;
import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Tramo;
import org.iesalandalus.programacion.reservasaulas.vista.iugrafica.utilidades.Dialogos;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Controlador del fichero fxml InsertarReserva
 * 
 * @author Juan Antonio Manzano Plaza
 * @version 4
 *
 */
public class ControladorInsertarReserva implements Initializable {

	private IControladorReservasAulas controladorMVC;

	private ObservableList<Reserva> reservas;

	@FXML private TextField tfAula;
	@FXML private TextField tfProfesor;
	@FXML private DatePicker dpDia;
	@FXML private RadioButton rbHora;
	@FXML private RadioButton rbTramo;
	@FXML private TextField tfHora;
	@FXML private Label lbHora;
	@FXML private Label lbTramo;
	@FXML private RadioButton rbManana;
	@FXML private RadioButton rbTarde;

	private ToggleGroup tgTipoPermanencia;
	private ToggleGroup tgTipoTramo;

	private static final String CORREO_VALIDO = "a@a.a";
	private static final int PUESTOS_VALIDOS = 50;

	/**
	 * Método initialize de la interfaz Initializable
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inicializaCalendario();
		agrupaRbTipoPermanencia();
		agrupaRbTipoTramo();
		rbManana.setVisible(false);
		rbTarde.setVisible(false);
		lbTramo.setVisible(false);
	}

	/**
	 * Método set para el atributo aula
	 * 
	 * @param aula el aula que obtiene al llamar a la clase desde el menú contextual de la TableView de Aulas
	 */
	public void setAula(Aula aula) {
		tfAula.setText(aula.getNombre());
	}

	/**
	 * Método set para el atributo profesor
	 * 
	 * @param profesor el aula que obtiene al llamar a la clase desde el menú contextual de la TableView de Profesores
	 */
	public void setProfesor(Profesor profesor) {
		tfProfesor.setText(profesor.getNombre());
	}

	/**
	 * Método set para el controlador del modelo vista controlador
	 * 
	 * @param controlador el controlador del programa
	 */
	public void setControladorMVC(IControladorReservasAulas controlador) {
		controladorMVC = controlador;
	}

	/**
	 * Método set para la ObservableList de reservas
	 * 
	 * @param reservas la lista de reservas de la ventana principal
	 */
	public void setReservas(ObservableList<Reserva> reservas) {
		this.reservas = reservas;
	}

	/**
	 * Método que inicializa el calenddario de la escena
	 */
	public void inicializaCalendario() {
		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>(){

			@Override
			public DateCell call(final DatePicker param) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						LocalDate mesSiguiente = LocalDate.now().plusMonths(1);
						if(item.isBefore(LocalDate.of(mesSiguiente.getYear(), mesSiguiente.getMonth(), 1))) {
							setDisable(true);
							setStyle("-fx-background-color: #C9C9C9;");
						}
					}
				};
			}

		};
		dpDia.setDayCellFactory(dayCellFactory);

	}

	/**
	 * Método que agrupa los RadioButton correspondientes al tipo de permanencia en un ToggleGroup
	 */
	public void agrupaRbTipoPermanencia() {
		tgTipoPermanencia = new ToggleGroup();
		rbHora.setToggleGroup(tgTipoPermanencia);
		rbTramo.setToggleGroup(tgTipoPermanencia);
		rbHora.setSelected(true);
		tgTipoPermanencia.selectedToggleProperty().addListener((observable, oldValue, newValue) -> muestraHora());
	}

	/**
	 * Método que agrupa los RadioButton correspondientes al tipo de tramo en un ToggleGroup
	 */
	public void agrupaRbTipoTramo() {
		tgTipoTramo = new ToggleGroup();
		rbManana.setToggleGroup(tgTipoTramo);
		rbTarde.setToggleGroup(tgTipoTramo);
		rbManana.setSelected(true);
	}

	/**
	 * Muestra u oculta el Label, TextField y RadioButtons correspondientes al tipo de Permanencia seleccionada
	 */
	private void muestraHora() {
		RadioButton seleccionado = (RadioButton) tgTipoPermanencia.getSelectedToggle();

		if(seleccionado == rbHora) {
			tfHora.setVisible(true);
			lbHora.setVisible(true);
			lbTramo.setVisible(false);
			rbManana.setVisible(false);
			rbTarde.setVisible(false);
		} else {
			tfHora.setVisible(false);
			lbHora.setVisible(false);
			lbTramo.setVisible(true);
			rbManana.setVisible(true);
			rbTarde.setVisible(true);
		}
	}

	/**
	 * Método que gestiona el evento de pulsar el botón aceptar
	 * 
	 * @param event el evento que gestiona
	 */
	@FXML private void aceptar (ActionEvent event) {
		//Declaración de variables necesarias para el modelo.
		Reserva reserva = null;
		String hora = tfHora.getText();
		LocalDate dia = dpDia.getValue();
		boolean error = false;
		Profesor profesor = null;
		Aula aula = null;
		
		//Declaración de variables necesarias para la ejecución.
		RadioButton permanenciaTipo = (RadioButton) tgTipoPermanencia.getSelectedToggle();
		RadioButton tramoTipo = (RadioButton) tgTipoTramo.getSelectedToggle();
		Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		//Ejecucion
		try {
			profesor = controladorMVC.buscarProfesor(new Profesor(tfProfesor.getText(), CORREO_VALIDO));
		} catch(Exception e) {
			Dialogos.mostrarDialogoError("Error en el profesor", e.getMessage());
			error = true;
		}
		if(!error) {
		try {
			aula = controladorMVC.buscarAula(new Aula(tfAula.getText(), PUESTOS_VALIDOS));
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Error en el aula", e.getMessage());
		}
		}
		if(!error && profesor==null) {
			Dialogos.mostrarDialogoError("Profesor inexistente", "El profesor especificado no existe");
			error = true;
		}
		if(!error && aula==null) {
			Dialogos.mostrarDialogoError("Aula inexistente", "El aula especificada no existe");
			error = true;
		}
		
		if(!error && permanenciaTipo == rbHora) {
			try {
				reserva = new Reserva(profesor, aula, new PermanenciaPorHora(dia, hora));
				controladorMVC.realizarReserva(reserva);
				reservas.add(reserva);
				Dialogos.mostrarDialogoInformacion("Realizar reserva", "Reserva realizada correctamente");
				escenario.close();
			} catch (Exception e) {
				Dialogos.mostrarDialogoError("Realizar reserva", e.getMessage());
			}
		}
		
		if(!error && permanenciaTipo == rbTramo) {
			if(tramoTipo == rbManana) {
				try {
					reserva =new Reserva(profesor, aula, new PermanenciaPorTramo(dia, Tramo.MANANA));
					controladorMVC.realizarReserva(reserva);
					reservas.add(reserva);
					Dialogos.mostrarDialogoInformacion("Realizar reserva", "Reserva realizada correctamente");					
					escenario.close();
				} catch (Exception e) {
					Dialogos.mostrarDialogoError("Realizar reserva", e.getMessage());
					error = true;
				}
			}
			if(!error && tramoTipo == rbTarde) {
				try {
					reserva = new Reserva(profesor, aula, new PermanenciaPorTramo(dia, Tramo.TARDE));
					controladorMVC.realizarReserva(reserva);
					reservas.add(reserva);
					Dialogos.mostrarDialogoInformacion("Realizar reserva", "Reserva realizada correctamente");
					escenario.close();
				} catch (Exception e) {
					Dialogos.mostrarDialogoError("Realizar reserva", e.getMessage());
					error = true;
				}
			}
		}
	}

	/**
	 * Método que gestiona el evento de haber pulsado el botón cancelar
	 * 
	 * @param event el evento que gestiona
	 */
	@FXML private void cancelar(ActionEvent event) {
		Stage escenario = (Stage)((Node) event.getSource()).getScene().getWindow();
		if(Dialogos.mostrarDialogoConfirmaion("Salir", "¿Estás seguro de que no quieres realizar una reserva?", escenario))
			escenario.close();
		else
			event.consume();
	}

}
