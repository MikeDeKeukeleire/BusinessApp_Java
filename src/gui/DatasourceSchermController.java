package gui;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.NoResultException;

import domein.Datasource;
import domein.DomeinController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class DatasourceSchermController extends BorderPane implements Initializable {

	@FXML
	private BorderPane borderpane;

	@FXML
	private Button btnVoegToe;

	@FXML
	private Label lblDatasource;

	@FXML
	private ListView<String> lvDatasources;

	@FXML
	private Label lblData;

	@FXML
	private Button btnBewerkDatasource;

	@FXML
	private Button btnVerwijderDatasource;

	@FXML
	private Pane pane;

	@FXML
	private Button btnAnnuleren;

	@FXML
	private Button btnBevestigen;

	@FXML
	private Label lblBevestiging;

	@FXML
	private Label lblNieuweNaam;

	@FXML
	private TextField txfNieuweNaamDatasource;

	@FXML
	private Label lblWaarschuwing;

	private TableView<String> tblvwDatasourceTableView;

	private List<String> namenLijst;

	private List<Datasource> datasourceLijst;

	private String geselecteerdeDatasource;

	private File[] files;

	private Alert alert;

	private String actie;

	private DomeinController d;

	public DatasourceSchermController(DomeinController d) {

		this.d = d;
		namenLijst = new ArrayList<>();
		refreshDatasourceLijst();
		zetNamenInLijst();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("DatasourceScherm.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		verbergItems();
		tblvwDatasourceTableView = new TableView<>();
	}

	private void verbergItems() {

		btnBevestigen.setVisible(false);
		btnAnnuleren.setVisible(false);
		txfNieuweNaamDatasource.setVisible(false);
		lblNieuweNaam.setVisible(false);
		lblBevestiging.setVisible(false);
		lblWaarschuwing.setVisible(false);

	}

	public void refreshDatasourceLijst() {
		datasourceLijst = d.getDatasources();
	}

	// Alle namen van de datasources toevoegen in een lijst
	private void zetNamenInLijst() {
		datasourceLijst.forEach(item -> namenLijst.add(item.getNaam()));
		Collections.sort(namenLijst);
	}

	// Geeft data weer van de geselecteerde datasource
	private List<String> getDataVanDatasource() {
		return d.getDataVanDatasource(geselecteerdeDatasource).getData();
	}

	private List<Double> getAggregatedData() {
		return d.getAggregatedData(geselecteerdeDatasource);
	}

	@FXML
	private void bevestigActie(ActionEvent event) {

		if (geselecteerdeDatasource != null) {

			if (actie == "bewerken") {
				if (txfNieuweNaamDatasource.getText().length() > 2
						&& !namenLijst.contains(txfNieuweNaamDatasource.getText())) {

					d.updateDatasourceNaam(geselecteerdeDatasource, txfNieuweNaamDatasource.getText());

					namenLijst.remove(geselecteerdeDatasource);
					namenLijst.add(txfNieuweNaamDatasource.getText());

					lvDatasources.getItems().remove(geselecteerdeDatasource);
					lvDatasources.getItems().add(txfNieuweNaamDatasource.getText());
					verbergItems();

				} else
					showDatasourceNaamAlert();

			} else if (actie == "verwijderen") {
				String huidigeDatasource = geselecteerdeDatasource;

				d.verwijderDatasource(huidigeDatasource);
				lvDatasources.getItems().remove(huidigeDatasource);
				namenLijst.remove(huidigeDatasource);
				verbergItems();
			}
		}
		if (actie == "toevoegen") {

			if (!namenLijst.contains(txfNieuweNaamDatasource.getText())) {
				readFile(files[0]);
				verbergItems();
			} else
				showDatasourceNaamAlert();
		}
	}

	@FXML
	private void annuleerActie(ActionEvent event) {
		verbergItems();
		tblvwDatasourceTableView.setVisible(true);
	}

	@FXML
	private void voegDatasourceToe(ActionEvent event) {

		FileDialog dialog = new FileDialog((Frame) null, "Selecteer een bestand");
		dialog.setMode(FileDialog.LOAD);
		dialog.setFile("*.csv");
		dialog.setVisible(true);

		// Indien files geselecteerd
		if (dialog.getFiles().length != 0) {

			btnBevestigen.setVisible(true);
			btnAnnuleren.setVisible(true);
			txfNieuweNaamDatasource.setVisible(true);
			lblBevestiging.setVisible(true);
			tblvwDatasourceTableView.setVisible(false);

			lblBevestiging.setText("Hoe wilt u deze datasource benoemen?");

			actie = "toevoegen";
			setFiles(dialog.getFiles());
		}
	}

	@FXML
	private void bewerkDatasource(ActionEvent event) {

		if (geselecteerdeDatasource != null) {

			verbergItems();
			btnBevestigen.setVisible(true);
			btnAnnuleren.setVisible(true);
			txfNieuweNaamDatasource.setVisible(true);
			lblNieuweNaam.setVisible(true);
			tblvwDatasourceTableView.setVisible(false);

			lblNieuweNaam.setText("Geef een nieuwe naam in voor " + geselecteerdeDatasource);
			actie = "bewerken";
		}
	}

	@FXML
	private void verwijderDatasource(ActionEvent event) {

		if (geselecteerdeDatasource != null) {

			verbergItems();
			btnBevestigen.setVisible(true);
			btnAnnuleren.setVisible(true);
			lblBevestiging.setVisible(true);
			tblvwDatasourceTableView.setVisible(false);

			lblBevestiging.setText("Wilt u " + geselecteerdeDatasource + " verwijderen?");
			actie = "verwijderen";
		}
	}

	private void setFiles(File[] files) {
		this.files = files;
	}

	private void readFile(File file) {

		try {
			List<String> lines = Files.readAllLines(Paths.get(file.toURI()));
			System.out.println(lines);

			if (txfNieuweNaamDatasource.getText().length() > 2)
				maakDatasource(txfNieuweNaamDatasource.getText(), lines);
			else
				showDatasourceNaamAlert();

		} catch (IOException e) {
			alert = new Alert(AlertType.ERROR);
			alert.setContentText("Error tijdens inlezen bestand");
			alert.show();
			System.err.println("ERROR IN READFILE");
			e.printStackTrace();
		}
	}

	private void showDatasourceNaamAlert() {
		lblWaarschuwing.setText("De naam moet minstens 2 karakters\n bevatten en moet uniek zijn!");
		lblWaarschuwing.setVisible(true);
	}

	private void maakDatasource(String naam, List<String> data) {
		namenLijst.add(naam);
		lvDatasources.getItems().add(naam);
		d.insertDatasource(naam, data);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		lvDatasources.getItems().addAll(namenLijst);
		lvDatasources.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

				geselecteerdeDatasource = lvDatasources.getSelectionModel().getSelectedItem();
				lblDatasource.setText(geselecteerdeDatasource);

				try {

					borderpane.setCenter(null);
					tblvwDatasourceTableView = new CSVTableView(";", getDataVanDatasource());
					tblvwDatasourceTableView.setLayoutX(240);
					tblvwDatasourceTableView.setLayoutY(160);
					borderpane.setCenter(tblvwDatasourceTableView);

				} catch (IOException | NoResultException ex) {
					ex.printStackTrace();
				}
			}
		});
	}

}

class CSVTableView extends TableView<String> {
	public CSVTableView(String delimiter, List<String> lines) throws IOException {

		String[] firstRow = lines.get(0).split(delimiter);

		for (String columnName : firstRow) {
			TableColumn<String, String> column = new TableColumn<>(columnName);
			this.getColumns().add(column);

			column.setCellValueFactory(cellDataFeatures -> {
				String values = cellDataFeatures.getValue();
				String[] cells = values.split(delimiter);
				int columnIndex = cellDataFeatures.getTableView().getColumns()
						.indexOf(cellDataFeatures.getTableColumn());
				if (columnIndex >= cells.length) {
					return new SimpleStringProperty("");
				} else {
					return new SimpleStringProperty(cells[columnIndex]);
				}
			});

			this.setItems(FXCollections.observableArrayList(lines));

			// eerste rij met hoofding verwijderen
			this.getItems().remove(0);
		}
	}
}