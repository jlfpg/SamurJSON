package vista;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import application.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import accesoDatos.AccesoHibernate;
import accesoDatos.BDManager;
import accesoDatos.*;
import modelo.Instalacion;

public class InstalacionController {
	private Main programaPrincipal;
	private Connection connection;
	private ObservableList<Instalacion> masterData3 = FXCollections.observableArrayList();
	private BDManager fichero;
	private AccesoHibernate hiber;
	private AccesoJSONRemoto jsonRmt;
	private AccesoMongo mongoBD;
	private FicherosTexto textFich;
	Instalacion em;
	HashMap<Integer, Instalacion> instalacionesCreadas = new HashMap<Integer, Instalacion>();
	ObservableList<String> options = FXCollections.observableArrayList("Hibernate", "JSON", "Mongodb", "BDManager",
			"Ficheros");

	@FXML
	private TextField instalacionFilterField;
	@FXML
	private ComboBox<String> adatMenu;
	@FXML
	private Button borrar;
	@FXML
	private Button borrarTodo;
	@FXML
	private Button refrescar;
	@FXML
	private TableView<Instalacion> lugarTable;
	@FXML
	private TableColumn<Instalacion, String> codParque;
	@FXML
	private TableColumn<Instalacion, String> nombre;
	@FXML
	private TableColumn<Instalacion, String> direccion;
	@FXML
	private TableColumn<Instalacion, String> telefono;
	@FXML
	private Button btnLimpiar;
	@FXML
	private TextField txtfCodParque;
	@FXML
	private TextField txtfNombre;
	@FXML
	private TextField txtfDireccion;
	@FXML
	private TextField txtfTelefono;
	@FXML
	private Button insUp;

	public InstalacionController() {

	}

	@FXML
	public void initialize() {
		estilos();
		adatMenu.getItems().addAll(options);

	}

	@FXML
	public void clickItem(MouseEvent event) {
		if (event.getClickCount() == 1) // Checking double click
		{
			Instalacion ins = lugarTable.getSelectionModel().getSelectedItem();
			txtfCodParque.setText(String.valueOf(ins.getCodparque()));
			txtfDireccion.setText(ins.getDireccion());
			txtfNombre.setText(ins.getNombre());
			if (ADATEleccion().equals("Mongodb")) {
				txtfTelefono.setText(String.valueOf(ins.getTelefonoM()));
			} else {
				txtfTelefono.setText(ins.getTelefono());
			}
		}
	}

	private void estilos() {
		Image clos = new Image(getClass().getResourceAsStream("refresh.png"));
		refrescar.setGraphic(new ImageView(clos));
	}

	private String ADATEleccion() {
		return adatMenu.getValue();
	}

	@FXML
	private void limpiar() {
		txtfCodParque.clear();
		txtfDireccion.clear();
		txtfNombre.clear();
		txtfTelefono.clear();
	}

	@FXML
	private void insUpInstal() {
		if (ADATEleccion().equals("Hibernate")) {
			// hiber=new AccesoHibernate();
			em = new Instalacion();
			em.setNombre(txtfNombre.getText());
			em.setDireccion(txtfDireccion.getText());
			em.setTelefono(txtfTelefono.getText());
			if (txtfCodParque.getText().isEmpty()) {
				instalacionesCreadas.put(1, em);
				hiber.guardarInstalacion(instalacionesCreadas);
				instalacionesCreadas.clear();
				em = null;

			} else {
				em.setCodparque(Integer.parseInt(txtfCodParque.getText()));
				instalacionesCreadas.put(1, em);
				hiber.updateInstalacion(instalacionesCreadas);
				instalacionesCreadas.clear();
				em = null;
			}
		} else if (ADATEleccion().equals("JSON")) {
			jsonRmt = new AccesoJSONRemoto();
			em = new Instalacion();
			em.setNombre(txtfNombre.getText());
			em.setDireccion(txtfDireccion.getText());
			em.setTelefono(txtfTelefono.getText());
			if (txtfCodParque.getText().isEmpty()) {
				instalacionesCreadas.put(1, em);
				jsonRmt.guardarInstalacion(instalacionesCreadas);
				instalacionesCreadas.clear();
				em = null;

			} else {
				em.setCodparque(Integer.parseInt(txtfCodParque.getText()));
				instalacionesCreadas.put(1, em);
				jsonRmt.updateInstalacion(instalacionesCreadas);
				instalacionesCreadas.clear();
				em = null;

			}
		} else if (ADATEleccion().equals("Mongodb")) {
			mongoBD = new AccesoMongo();
			em = new Instalacion();
			em.setNombre(txtfNombre.getText());
			em.setDireccion(txtfDireccion.getText());
			em.setTelefonoM(Integer.parseInt(txtfTelefono.getText()));
			if (txtfCodParque.getText().isEmpty()) {
				mongoBD.guardarInstalacionM(em);
				em = null;

			} else {
				em.setCodparque(Integer.parseInt(txtfCodParque.getText()));
				mongoBD.actualizarInstalacionM(em);
				em = null;

			}
		} else if (ADATEleccion().equals("BDManager")) {
			// BDManager fichero=new BDManager();
			if (txtfCodParque.getText().isEmpty()) {

			} else {

			}
		} else if (ADATEleccion().equals("Ficheros")) {
			textFich = new FicherosTexto();
			if (txtfCodParque.getText().isEmpty()) {

			} else {

			}
		}
	}

	@FXML
	private void borrarInstal() {

		int inde = lugarTable.getSelectionModel().getSelectedIndex();
		em = lugarTable.getSelectionModel().getSelectedItem();
		masterData3.remove(inde);
		if (ADATEleccion().equals("Hibernate")) {
			System.out.println(ADATEleccion());
			// hiber=new AccesoHibernate();
			instalacionesCreadas.put(em.getCodparque(), em);
			hiber.deleteInstalacion(instalacionesCreadas);
			instalacionesCreadas.clear();
			em=null;
		} else if (ADATEleccion().equals("JSON")) {
			System.out.println(ADATEleccion());
			jsonRmt = new AccesoJSONRemoto();
			instalacionesCreadas.put(1, em);
			jsonRmt.deleteInstalacion(instalacionesCreadas);
			instalacionesCreadas.clear();
			em=null;

		} else if (ADATEleccion().equals("Mongodb")) {
			System.out.println(ADATEleccion());
			mongoBD = new AccesoMongo();
			mongoBD.eliminarInstalacionM(em);
			instalacionesCreadas.clear();
			em=null;
		} else if (ADATEleccion().equals("BDManager")) {
			System.out.println(ADATEleccion());
			// BDManager fichero=new BDManager();
			instalacionesCreadas.clear();
			em=null;

		} else if (ADATEleccion().equals("Ficheros")) {
			System.out.println(ADATEleccion());
			textFich = new FicherosTexto();
			instalacionesCreadas.clear();
			em=null;
		}

	}

	@FXML
	private void borrarTodoLugar() {
		if (ADATEleccion().equals("Hibernate")) {
			System.out.println(ADATEleccion());
			// hiber=new AccesoHibernate();
			masterData3.clear();
		} else if (ADATEleccion().equals("JSON")) {
			System.out.println(ADATEleccion());
			jsonRmt = new AccesoJSONRemoto();
			masterData3.clear();

		} else if (ADATEleccion().equals("Mongodb")) {
			System.out.println(ADATEleccion());
			mongoBD = new AccesoMongo();
			masterData3.clear();
		} else if (ADATEleccion().equals("BDManager")) {
			System.out.println(ADATEleccion());
			// BDManager fichero=new BDManager();
			masterData3.clear();

		} else if (ADATEleccion().equals("Ficheros")) {
			System.out.println(ADATEleccion());
			textFich = new FicherosTexto();
			masterData3.clear();
		}

	}

	@FXML
	private void cargarLugar() throws Exception {
		if (ADATEleccion().equals("Hibernate")) {
			// System.out.println(ADATEleccion());
			// //hiber=new AccesoHibernate();
			// masterData3.clear();
			// for (int i = 0; i < hiber.obtenerInstalacion().size(); i++) {
			// masterData3.add((Instalacion)
			// hiber.obtenerInstalacion().values().toArray()[i]);
			// }
			// telefono.setCellValueFactory(cellData -> new
			// SimpleStringProperty(cellData.getValue().getTelefono()));

		} else if (ADATEleccion().equals("JSON")) {
			System.out.println(ADATEleccion());
			jsonRmt = new AccesoJSONRemoto();
			masterData3.clear();
			for (int i = 0; i < jsonRmt.obtenerInstalacion().size(); i++) {
				masterData3.add((Instalacion) jsonRmt.obtenerInstalacion().values().toArray()[i]);
			}
			telefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));

		} else if (ADATEleccion().equals("Mongodb")) {
			System.out.println(ADATEleccion());
			mongoBD = new AccesoMongo();
			masterData3.clear();

			for (int i = 0; i < mongoBD.obtenerInstalacionM().size(); i++) {
				masterData3.add((Instalacion) mongoBD.obtenerInstalacionM().values().toArray()[i]);
			}
			telefono.setCellValueFactory(
					cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTelefonoM())));
		} else if (ADATEleccion().equals("BDManager")) {
			// System.out.println(ADATEleccion());
			// // BDManager fichero=new BDManager();
			// masterData3.clear();
			// for (int i = 0; i < fichero.obtenerInstalacion().size(); i++) {
			// masterData3.add((Instalacion)
			// fichero.obtenerInstalacion().values().toArray()[i]);
			// }
			// telefono.setCellValueFactory(cellData -> new
			// SimpleStringProperty(cellData.getValue().getTelefono()));
			//
			//
		} else if (ADATEleccion().equals("Ficheros")) {
			// System.out.println(ADATEleccion());
			// textFich=new FicherosTexto();
			// masterData3.clear();
			// for (int i = 0; i < textFich.leeTodos().size(); i++) {
			// masterData3.add((Instalacion) textFich.leeTodos().values().toArray()[i]);
			// }
			// telefono.setCellValueFactory(cellData -> new
			// SimpleStringProperty(cellData.getValue().getTelefono()));
			//
		}

		codParque.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCodparque())));
		nombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
		direccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));

		FilteredList<Instalacion> filteredData3 = new FilteredList<>(masterData3, p -> true);
		instalacionFilterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData3.setPredicate(person3 -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				if (!ADATEleccion().equals("Mongodb")) {
					if (String.valueOf(person3.getCodparque()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches first name.
					} else if (person3.getDireccion().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches last name.
					} else if (person3.getNombre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches nota.
					} else if (person3.getTelefono().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true;
					}
				} else if (ADATEleccion().equals("Mongodb")) {
					if (String.valueOf(person3.getCodparque()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches first name.
					} else if (person3.getDireccion().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches last name.
					} else if (person3.getNombre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true; // Filter matches nota.
					} else if (String.valueOf(person3.getTelefonoM()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true;
					}
				}

				return false; // Does not match.
			});
		});
		SortedList<Instalacion> sortedData3 = new SortedList<>(filteredData3);
		sortedData3.comparatorProperty().bind(lugarTable.comparatorProperty());
		lugarTable.setItems(sortedData3);

	}

	@SuppressWarnings("unused")
	private Stage ventana;

	public void setProgramaPrincipal(Main programaPrincipal) {
		this.programaPrincipal = programaPrincipal;
	}

	public void setStagePrincipal(Stage ventana) {
		this.ventana = ventana;
	}

}
