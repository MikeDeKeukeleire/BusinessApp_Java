package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.CheckListView;

import domein.AggregatedData;
import domein.DomeinController;
import domein.MvoDoelstelling;
import domein.MvoDoelstellingComponent;
import domein.SubMvoDoelstelling;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class MVOdoelstellingController extends BorderPane implements Initializable {
	@FXML
	private ListView<MvoDoelstelling> lstDoelstellingen;
	@FXML
	private ListView<SubMvoDoelstelling> lstSubDoelstellingen;
	@FXML
	private Button btnVoegToe;
	@FXML
	private ImageView imgIcoon;
	@FXML
	private Label lblSelectie;
	@FXML
	private Button btnVerwijderDoelstelling;
	@FXML
	private Button btnBewerkDoelstelling;
	@FXML
	private Label lblWaarschuwingen;
	@FXML
	private CheckListView<SubMvoDoelstelling> clvSubDoelstellingen;
	@FXML
	private CheckListView<AggregatedData> clvDatasources;
	@FXML
	private CheckComboBox<String> cbKiesDoelstellingen;
	@FXML
	private Label lblIcoon;
	@FXML
	private Label lblKiesSubDoelstellingen;
	@FXML
	private Label lblNaam;
	@FXML
	private TextField txfIcoon;
	@FXML
	private TextField txfNaam;
	@FXML
	private Button btnAnnuleren;

	@FXML
	private Button btnBevestigen;

	private String actie;
	private DomeinController dc;
	private MvoDoelstellingComponent geselecteerdeMvoDoelstellingComponent;

	// Event Listener on Button[#btnVoegToe].onAction
	@FXML
	public void voegDoelstellingToe(ActionEvent event) {
		verbergOfToonItems(true);

		List<String> lijst = new ArrayList<>();
		dc.getAllMvoDoelstellingen()
				.forEach(el -> el.getSubMVODoelstellingen().forEach(submvo -> lijst.add(submvo.getNaam())));

		cbKiesDoelstellingen.getItems().addAll(lijst);
		actie = "toevoegen";
	}

	@FXML
	void annuleerActie(ActionEvent event) {
		verbergOfToonItems(false);
	}

	@FXML
	void bevestigActie(ActionEvent event) {

		if (actie == "bewerken") {
			dc.bewerkMvoDoelstelling(geselecteerdeMvoDoelstellingComponent.getId(), txfNaam.getText(),
					txfIcoon.getText());
			lstDoelstellingen.getItems().clear();
			cbKiesDoelstellingen.getItems().clear();
			getMVODoelstellingen();
		}

		if (actie == "toevoegen") {

			List<MvoDoelstellingComponent> newMvos = new ArrayList<>();
			cbKiesDoelstellingen.getCheckModel().getCheckedItems()
					.forEach(el -> newMvos.add(dc.getSubDoelstellingByName(el)));
			dc.voegMvoDoelstellingToe(999, txfNaam.getText(), txfIcoon.getText(), newMvos);
			lstDoelstellingen.getItems().clear();
			getMVODoelstellingen();
		}

		verbergOfToonItems(false);
	}

	// Event Listener on Button[#btnVerwijderDoelstelling].onAction
	@FXML
	public void verwijderDoelstelling(ActionEvent event) {
		if (geselecteerdeMvoDoelstellingComponent != null) {
			String huidigeDoelstelling = geselecteerdeMvoDoelstellingComponent.getNaam();
			dc.verwijderMvoDoelstelling(huidigeDoelstelling);

			lstDoelstellingen.getItems().clear();
			getMVODoelstellingen();
		}
	}

	// Event Listener on Button[#btnBewerkDoelstelling].onAction
	@FXML
	public void bewerkDoelstelling(ActionEvent event) {
		if (geselecteerdeMvoDoelstellingComponent != null) {
			verbergOfToonItems(true);
			lblKiesSubDoelstellingen.setVisible(false);
			cbKiesDoelstellingen.setVisible(false);
			actie = "bewerken";
		}
	}

	private void verbergOfToonItems(boolean tonen) {
		lblIcoon.setVisible(tonen);
		lblKiesSubDoelstellingen.setVisible(tonen);
		lblNaam.setVisible(tonen);
		txfIcoon.setVisible(tonen);
		txfNaam.setVisible(tonen);
		cbKiesDoelstellingen.setVisible(tonen);
		btnBevestigen.setVisible(tonen);
		btnAnnuleren.setVisible(tonen);
	}

	public MVOdoelstellingController(DomeinController dc) {
		if (dc == null)
			return;
		this.dc = dc;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("MVOdoelstelling.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		verbergOfToonItems(false);
	}

	public void getMVODoelstellingen() {
		this.lstDoelstellingen.getItems().addAll(this.dc.getAllMvoDoelstellingen());
		this.lstDoelstellingen.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<MvoDoelstelling>() {

					@Override
					public void changed(ObservableValue<? extends MvoDoelstelling> arg0, MvoDoelstelling arg1,
							MvoDoelstelling arg2) {
						if (lstSubDoelstellingen.getItems() != null)
							lstSubDoelstellingen.getItems().clear();
						if (clvSubDoelstellingen.getItems() != null)
							clvSubDoelstellingen.getItems().clear();
						if (clvDatasources.getItems() != null)
							clvDatasources.getItems().clear();

						geselecteerdeMvoDoelstellingComponent = (MvoDoelstelling) lstDoelstellingen.getSelectionModel()
								.getSelectedItem();
						if (geselecteerdeMvoDoelstellingComponent != null) {
							getSubMVODoelstellingen();
							showMvoComponent(geselecteerdeMvoDoelstellingComponent);
						}
					}

				});
	}

	public void getSubMVODoelstellingen() {
		this.lstSubDoelstellingen.getItems()
				.addAll(((MvoDoelstelling) geselecteerdeMvoDoelstellingComponent).getSubMVODoelstellingen());
		this.lstSubDoelstellingen.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<SubMvoDoelstelling>() {

					@Override
					public void changed(ObservableValue<? extends SubMvoDoelstelling> arg0, SubMvoDoelstelling arg1,
							SubMvoDoelstelling arg2) {
						if (clvSubDoelstellingen.getItems() != null)
							clvSubDoelstellingen.getItems().clear();
						if (clvDatasources.getItems() != null)
							clvDatasources.getItems().clear();

						geselecteerdeMvoDoelstellingComponent = lstSubDoelstellingen.getSelectionModel()
								.getSelectedItem();
						if (geselecteerdeMvoDoelstellingComponent != null)
							showMvoComponent(geselecteerdeMvoDoelstellingComponent);
					}
				});
	}

	public void showSubMvoDoestellingen() {
		this.clvSubDoelstellingen.getItems()
				.addAll(((MvoDoelstelling) geselecteerdeMvoDoelstellingComponent).getSubMVODoelstellingen());
	}

	public void showDatasources() {
		this.clvDatasources.getItems()
				.addAll(((SubMvoDoelstelling) geselecteerdeMvoDoelstellingComponent).getDatasources());
	}

	public void showMvoComponent(MvoDoelstellingComponent doelstelling) {
		if (doelstelling == null) {
			imgIcoon.setImage(new Image("https://cdn-icons-png.flaticon.com/512/1828/1828884.png"));
			lblSelectie.setText("Geen selectie");
			lblWaarschuwingen.setVisible(true);
			lblWaarschuwingen.setText("Geen MVO-doelstelling geselecteerd");
			btnBewerkDoelstelling.setDisable(true);
			btnVerwijderDoelstelling.setDisable(true);
			clvSubDoelstellingen.setDisable(true);
			clvSubDoelstellingen.setEditable(false);
			clvDatasources.setDisable(true);
			clvDatasources.setEditable(false);
		}

		if (doelstelling instanceof MvoDoelstelling) {
			imgIcoon.setImage(new Image(doelstelling.getIcoon()));
			lblSelectie.setText(doelstelling.getNaam());
			lblWaarschuwingen.setVisible(false);
			btnBewerkDoelstelling.setDisable(false);
			btnVerwijderDoelstelling.setDisable(false);
			clvSubDoelstellingen.setDisable(false);
			clvSubDoelstellingen.setEditable(false);
			clvDatasources.setDisable(true);
			clvDatasources.setEditable(false);

			showSubMvoDoestellingen();
		}

		if (doelstelling instanceof SubMvoDoelstelling) {
			imgIcoon.setImage(new Image(doelstelling.getIcoon()));
			lblSelectie.setText(doelstelling.getNaam());
			lblWaarschuwingen.setVisible(false);
			btnBewerkDoelstelling.setDisable(false);
			btnVerwijderDoelstelling.setDisable(false);
			clvSubDoelstellingen.setDisable(true);
			clvSubDoelstellingen.setEditable(false);
			clvDatasources.setDisable(false);
			clvDatasources.setEditable(false);

			showDatasources();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.getMVODoelstellingen();
		this.showMvoComponent(null);
	}
}
