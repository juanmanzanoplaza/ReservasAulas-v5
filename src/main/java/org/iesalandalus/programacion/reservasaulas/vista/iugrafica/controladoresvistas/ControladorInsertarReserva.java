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

	private static final DateTimeFormatter ER_DIA = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	private static final DateTimeFormatter ER_HORA = DateTimeFormatter.ofPattern("HH:mm");
	private static final String CORREO_VALIDO = "a@a.a";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inicializaCalendario();
		agrupaRbTipoPermanencia();
		agrupaRbTipoTramo();
		rbManana.setVisible(false);
		rbTarde.setVisible(false);
		lbTramo.setVisible(false);
	}
	
	public void setAula(Aula aula) {
		tfAula.setText(aula.getNombre());
	}
	
	public void setProfesor(Profesor profesor) {
		tfProfesor.setText(profesor.getNombre());
	}

	public void setControladorMVC(IControladorReservasAulas controlador) {
		controladorMVC = controlador;
	}

	public void setReservas(ObservableList<Reserva> reservas) {
		this.reservas = reservas;
	}

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

	public void agrupaRbTipoPermanencia() {
		tgTipoPermanencia = new ToggleGroup();
		rbHora.setToggleGroup(tgTipoPermanencia);
		rbTramo.setToggleGroup(tgTipoPermanencia);
		rbHora.setSelected(true);
		tgTipoPermanencia.selectedToggleProperty().addListener((observable, oldValue, newValue) -> muestraHora());
	}

	public void agrupaRbTipoTramo() {
		tgTipoTramo = new ToggleGroup();
		rbManana.setToggleGroup(tgTipoTramo);
		rbTarde.setToggleGroup(tgTipoTramo);
		rbManana.setSelected(true);
	}

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

	@FXML private void aceptar (ActionEvent event) {
		boolean error = false;
		Reserva reserva = null;
		LocalTime hora = null;
		String dia = null;

		try {
			dia = dpDia.getValue().format(ER_DIA);//LocalDate.parse(dpDia.getValue().format(ER_DIA), ER_DIA);
		} catch (DateTimeParseException e) {
			Dialogos.mostrarDialogoError("Insertar Reserva", "El formato del dia no es correcto.");
			error = true;
		}

		if(!error) {
			try {
				RadioButton permanenciaTipo = (RadioButton) tgTipoPermanencia.getSelectedToggle();
				Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();

				if(permanenciaTipo == rbHora) {
					try {
						hora = LocalTime.parse(tfHora.getText(), ER_HORA);
					} catch (DateTimeParseException e) {
						Dialogos.mostrarDialogoError("Insertar Reserva", "El formato de la hora no es correcto.");
						error = true;
					}
					if(!error)
						reserva = new Reserva(new Profesor(tfProfesor.getText(), CORREO_VALIDO), new Aula(tfAula.getText(), 10), new PermanenciaPorHora(dia.toString(), hora.toString()));
				} else {
					RadioButton tramo = (RadioButton) tgTipoTramo.getSelectedToggle();
					if(tramo == rbManana)
						reserva = new Reserva(new Profesor(tfProfesor.getText(), CORREO_VALIDO), new Aula(tfAula.getText(), 10), new PermanenciaPorTramo(dia.toString(), Tramo.MANANA));
					else
						reserva = new Reserva(new Profesor(tfProfesor.getText(), CORREO_VALIDO), new Aula(tfAula.getText(), 10), new PermanenciaPorTramo(dia.toString(), Tramo.TARDE));
				}
				controladorMVC.realizarReserva(reserva);
				reservas.add(reserva);
				escenario.close();

			} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
				Dialogos.mostrarDialogoError("Insertar Reserva", e.getMessage());
			}
		}
	}

	@FXML private void cancelar(ActionEvent event) {
		Stage escenario = (Stage)((Node) event.getSource()).getScene().getWindow();
		if(Dialogos.mostrarDialogoConfirmaion("Salir", "¿Estás seguro de que no quieres realizar una reserva?", escenario))
			escenario.close();
		else
			event.consume();
	}

}
