package org.iesalandalus.programacion.reservasaulas.vista.iugrafica.utilidades;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Clase que crea los diálogos de la aplicación
 * 
 * 
 * @version 5
 *
 */
public class Dialogos {

	private static final String CSS = "../iuGrafica.css";
	private static final String ID_BT_ACEPTAR = "btAceptar";
	private static final String ID_BT_CANCELAR = "btCancelar";

	/**
	 * Constructor privado para evitar que se instancien objetos de la clase
	 */
	private Dialogos() {
	}

	/**
	 * Método estático que crea un diálogo de error
	 * @param titulo el título de la ventana de diálogo
	 * @param contenido el contenido de la ventana de diálogo
	 * @param propietario la escena que lanza el diálogo
	 */
	public static void mostrarDialogoError(String titulo, String contenido, Stage propietario) {
		Alert dialogo = new Alert(AlertType.ERROR);
		dialogo.getDialogPane().getStylesheets().add(Dialogos.class.getResource(CSS).toExternalForm());
		((Button) dialogo.getDialogPane().lookupButton(ButtonType.OK)).setId(ID_BT_ACEPTAR);
		dialogo.setTitle(titulo);
		dialogo.setHeaderText(null);
		dialogo.setContentText(contenido);
		if(propietario != null) {
			dialogo.initModality(Modality.APPLICATION_MODAL);
			dialogo.initOwner(propietario);
		}
		dialogo.showAndWait();
		if(propietario != null)
			propietario.close();
	}

	/**
	 * Método estático que crea un diálogo de error
	 * @param titulo el título de la ventana de diálogo
	 * @param contenido el contenido de la ventana de diálogo
	 */
	public static void mostrarDialogoError(String titulo, String contenido) {
		Dialogos.mostrarDialogoError(titulo, contenido, null);
	}

	/**
	 * Método estático que crea un diálogo de información
	 * @param titulo el título de la ventana de diálogo
	 * @param contenido el contenido de la ventana de diálogo
	 * @param propietario la escena que lanza el diálogo
	 */
	public static void mostrarDialogoInformacion(String titulo, String contenido, Stage propietario) {
		Alert dialogo = new Alert(AlertType.INFORMATION);
		dialogo.getDialogPane().getStylesheets().add(Dialogos.class.getResource(CSS).toExternalForm());
		((Button) dialogo.getDialogPane().lookupButton(ButtonType.OK)).setId(ID_BT_ACEPTAR);
		dialogo.setTitle(titulo);
		dialogo.setHeaderText(null);
		dialogo.setContentText(contenido);
		if(propietario!=null) {
			dialogo.initModality(Modality.APPLICATION_MODAL);
			dialogo.initOwner(propietario);
		}
		dialogo.showAndWait();
		if(propietario != null)
			propietario.close();
	}

	/**
	 * Método estático que crea un diálogo de información
	 * @param titulo el título de la ventana de diálogo
	 * @param contenido el contenido de la ventana de diálogo
	 */
	public static void mostrarDialogoInformacion(String titulo, String contenido) {
		Dialogos.mostrarDialogoInformacion(titulo, contenido, null);
	}

	/**
	 * Método estático que crea un diálogo de advertencia
	 * @param titulo el título de la ventana de diálogo
	 * @param contenido el contenido de la ventana de diálogo
	 * @param propietario la escena que lanza el diálogo
	 */
	public static void mostrarDialogoAdvertencia(String titulo, String contenido, Stage propietario) {
		Alert dialogo = new Alert(AlertType.WARNING);
		dialogo.getDialogPane().getStylesheets().add(Dialogos.class.getResource(CSS).toExternalForm());
		((Button) dialogo.getDialogPane().lookupButton(ButtonType.OK)).setId(ID_BT_ACEPTAR);
		dialogo.setTitle(titulo);
		dialogo.setHeaderText(null);
		dialogo.setContentText(contenido);
		if(propietario != null) {
			dialogo.initModality(Modality.APPLICATION_MODAL);
			dialogo.initOwner(propietario);
		}
		dialogo.showAndWait();
		if(propietario!=null)
			propietario.close();
	}

	/**
	 * Método estático que crea un diálogo de advertencia
	 * @param titulo el título de la ventana de diálogo
	 * @param contenido el contenido de la ventana de diálogo
	 */
	public static void mostrarDialogoAdvertencia(String titulo, String contenido) {
		Dialogos.mostrarDialogoAdvertencia(titulo, contenido, null);
	}

	/**
	 * Método que crea un diálogo para obtener un texto introducido
	 * @param titulo el título de la ventana de diálogo
	 * @param contenido el contenido de la ventana de diálogo
	 * @return el texto introducido por teclado
	 */
	public static String mostrarDialogoTexto(String titulo, String contenido) {
		TextInputDialog dialogo = new TextInputDialog();
		dialogo.getDialogPane().getStylesheets().add(Dialogos.class.getResource(CSS).toExternalForm());
		((Button) dialogo.getDialogPane().lookupButton(ButtonType.OK)).setId(ID_BT_ACEPTAR);
		((Button) dialogo.getDialogPane().lookupButton(ButtonType.CANCEL)).setId(ID_BT_CANCELAR);
		dialogo.setTitle(titulo);
		dialogo.setHeaderText(null);
		dialogo.setContentText(contenido);

		Optional<String> respuesta = dialogo.showAndWait();
		return (respuesta.isPresent() ? respuesta.get() : null);
	}

	/**
	 * Método estático que crea un diálogo de confirmación
	 * @param titulo el título de la ventana de diálogo
	 * @param contenido el contenido de la ventana de diálogo
	 * @param propietario la escena que lanza el diálogo
	 * @return true si se pulsa aceptar, false si se pulsa cancelar
	 */
	public static boolean mostrarDialogoConfirmaion(String titulo, String contenido, Stage propietario) {
		Alert dialogo = new Alert(AlertType.CONFIRMATION);
		dialogo.getDialogPane().getStylesheets().add(Dialogos.class.getResource(CSS).toExternalForm());
		((Button) dialogo.getDialogPane().lookupButton(ButtonType.OK)).setId(ID_BT_ACEPTAR);
		((Button) dialogo.getDialogPane().lookupButton(ButtonType.CANCEL)).setId(ID_BT_CANCELAR);
		dialogo.setTitle(titulo);
		dialogo.setHeaderText(null);
		dialogo.setContentText(contenido);
		if(propietario != null) {
			dialogo.initModality(Modality.APPLICATION_MODAL);
			dialogo.initOwner(propietario);
		}

		Optional<ButtonType> respuesta = dialogo.showAndWait();
		return (respuesta.isPresent() && respuesta.get() == ButtonType.OK);
	}

}
