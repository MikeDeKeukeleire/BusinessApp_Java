package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.controlsfx.control.CheckComboBox;

import domein.Categorie;
import domein.DomeinController;
import domein.Sdg;
import domein.SubSdg;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class CategorieSchermController extends BorderPane {

	@FXML
	private Button btnAanpassenSubmit;

	@FXML
	private Button btnAnnuleren;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnVerwijderJa;

	@FXML
	private Button btnVerwijderNee;

	@FXML
	private Button btnEdit;

	@FXML
	private Button btnNewCat;

	@FXML
	private Button btnToevoegen;

	@FXML
	private ListView<Categorie> categorieListView;

	@FXML
	private CheckComboBox<String> cbSDG;

	@FXML
	private CheckComboBox<String> cbSubSDG;

	@FXML
	private ImageView imgIcoon;

	@FXML
	private Label lblCat;

	@FXML
	private Label lblIcoon;

	@FXML
	private Label lblNaam;

	@FXML
	private Label lblSdg;

	@FXML
	private Label lblSubSdg;

	@FXML
	private Label lblWaarschuwingen;

	@FXML
	private ListView<Sdg> sdgListView;

	@FXML
	private TextField txfIcoon;

	@FXML
	private TextField txfNaam;

	private DomeinController d;
	private Categorie geselecteerdeCategorie;
	private List<Sdg> sdgList;
	private List<SubSdg> subSdgList = new ArrayList<>();
	private List<Sdg> newSdg;
	private List<SubSdg> newSubSdg;
	private String naam, icoonUrl;

	// CONSTRUCTOR
	public CategorieSchermController(DomeinController d) {
		this.d = d;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieScherm.fxml"));
		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		getCategorieen();
		sdgsOpvullen();

		categorieDetails(false);
		btnAanpassenSubmit.setVisible(false);
		btnVerwijderJa.setVisible(false);
		btnVerwijderNee.setVisible(false);
		disableButtons();

		imgIcoon.setImage(new Image("https://cdn-icons-png.flaticon.com/512/1828/1828884.png"));
	}

	// CATEGORIE TOEVOEGEN COMPONENTEN TONEN
	@FXML
	void newCat(ActionEvent event) {
		categorieGlobaal(false);
		categorieDetails(true);

		lblWaarschuwingen.setText("Nieuwe categorie toevoegen");
		imgIcoon.setImage(null);

		cbSDG.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {

			@Override
			public void onChanged(Change<? extends String> arg0) {
				cbSubSDG.getItems().clear();
				newSdg = new ArrayList<>();
				cbSDG.getCheckModel().getCheckedItems().forEach(el -> newSdg.add(d.getSdgByName(el)));
				subSdgsOpvullen(newSdg);
			}

		});
	}

	// CATEGORIE TOEVOEGEN
	@FXML
	void submit(ActionEvent event) {
		naam = txfNaam.getText();
		icoonUrl = txfIcoon.getText();
		newSdg = new ArrayList<>();
		cbSDG.getCheckModel().getCheckedItems().forEach(el -> newSdg.add(d.getSdgByName(el)));
		newSubSdg = new ArrayList<>();
		cbSubSDG.getCheckModel().getCheckedItems().forEach(el -> newSubSdg.add(d.getSubSdgByNummering(el)));

		if (newSdg == null)
			newSdg = new ArrayList<>();

		List<Categorie> categorieen = d.getCategorieen();
		List<String> catNamen = new ArrayList<String>();
		categorieen.forEach(el -> catNamen.add(el.getNaam().toLowerCase()));

		if (catNamen.contains(naam.toLowerCase())) {
			lblWaarschuwingen.setText("Naam moet uniek zijn");
		} else if (naam.isBlank() || naam.isEmpty() || icoonUrl.isBlank() || icoonUrl.isEmpty() || newSdg.isEmpty()) {
			lblWaarschuwingen.setText("Ingevoerde waarden zijn onjuist, probeer opnieuw");
		} else if (!urlCheck(icoonUrl)) {
			lblWaarschuwingen.setText("Url moet met https beginnen");
		} else {
			maakCategorie(naam, icoonUrl, newSdg, newSubSdg);

			txfNaam.clear();
			txfIcoon.clear();
			cbSDG.getCheckModel().clearChecks();
			cbSubSDG.getCheckModel().clearChecks();
			cbSubSDG.getItems().clear();

			categorieGlobaal(true);
			categorieDetails(false);

			categorieListView.getItems().clear();
			getCategorieen();

			lblWaarschuwingen.setText("");
			imgIcoon.setImage(null);
			lblCat.setText("Selecteer een categorie");
		}
	}

	// CATEGORIE AANPASSEN COMPONENTEN TONEN
	@FXML
	void aanpassen(ActionEvent event) {
		if (geselecteerdeCategorie == null) {
			lblWaarschuwingen.setText("Selecteer een categorie om aan te passen");
		} else {
			categorieGlobaal(false);
			categorieDetails(true);
			btnAanpassenSubmit.setVisible(true);
			btnToevoegen.setVisible(false);

			imgIcoon.setImage(null);

			txfNaam.setText(geselecteerdeCategorie.getNaam());
			txfIcoon.setText(geselecteerdeCategorie.getUrlIcoon());
			geselecteerdeCategorie.getSdgs().forEach(el -> cbSDG.getCheckModel().check(el.getNaam()));
			subSdgsOpvullen(geselecteerdeCategorie.getSdgs());
			geselecteerdeCategorie.getSubSdgs().forEach(el -> cbSubSDG.getCheckModel().check(el.getNummering()));

			lblWaarschuwingen.setText("Categorie aanpassen");
		}
	}

	// CATEGORIE AANPASSEN
	@FXML
	void aanpassenSubmit(ActionEvent event) {
		naam = txfNaam.getText();
		icoonUrl = txfIcoon.getText();
		newSdg = new ArrayList<>();
		cbSDG.getCheckModel().getCheckedItems().forEach(el -> newSdg.add(d.getSdgByName(el)));
		newSubSdg = new ArrayList<>();
		cbSubSDG.getCheckModel().getCheckedItems().forEach(el -> newSubSdg.add(d.getSubSdgByNummering(el)));

		if (newSdg == null)
			newSdg = new ArrayList<>();

		List<Categorie> categorieen = d.getCategorieen();
		List<String> catNamen = new ArrayList<String>();
		categorieen.forEach(el -> catNamen.add(el.getNaam().toLowerCase()));

		if (naam.isBlank() || naam.isEmpty() || icoonUrl.isBlank() || icoonUrl.isEmpty() || newSdg.isEmpty()) {
			lblWaarschuwingen.setText("Ingevoerde waarden zijn onjuist, probeer opnieuw");
		} else if (!urlCheck(icoonUrl)) {
			lblWaarschuwingen.setText("Url moet met https beginnen");
		} else {
			updateCategorie(naam, icoonUrl, newSdg, newSubSdg);

			categorieGlobaal(true);
			categorieDetails(false);
			btnAanpassenSubmit.setVisible(false);

			txfNaam.clear();
			txfIcoon.clear();
			cbSDG.getCheckModel().clearChecks();
			cbSubSDG.getItems().clear();

			categorieListView.getItems().clear();
			getCategorieen();

			lblWaarschuwingen.setText("");
			lblCat.setText("Geen selectie");
			imgIcoon.setImage(new Image("https://cdn-icons-png.flaticon.com/512/1828/1828884.png"));
		}
	}

	// CATEGORIE TOEVOEGEN / AANPASSEN ANNULEREN
	@FXML
	void annuleren(ActionEvent event) {
		txfNaam.clear();
		txfIcoon.clear();
		cbSDG.getCheckModel().clearChecks();
		cbSubSDG.getItems().clear();

		categorieGlobaal(true);
		categorieDetails(false);
		btnAanpassenSubmit.setVisible(false);

		lblWaarschuwingen.setText("");
		lblCat.setText("");

		if (geselecteerdeCategorie == null) {
			imgIcoon.setImage(null);
		} else {
			lblCat.setText(geselecteerdeCategorie.toString());
			imgIcoon.setImage(new Image(geselecteerdeCategorie.getUrlIcoon()));
		}
	}

	// CATEGORIE VERWIJDEREN
	@FXML
	void verwijder(ActionEvent event) {
		if (geselecteerdeCategorie == null) {
			lblWaarschuwingen.setText("Selecteer een categorie om te verwijderen");
		} else {
			lblWaarschuwingen.setText("Deze categorie verwijderen?");

			btnVerwijderJa.setVisible(true);
			btnVerwijderNee.setVisible(true);
		}
	}

	// CATEGORIE VERWIJDEREN SUBMIT
	@FXML
	void verwijderSubmit(ActionEvent event) {
		d.verwijderCategorie(geselecteerdeCategorie.getId());
		categorieListView.getItems().remove(geselecteerdeCategorie);

		lblWaarschuwingen.setText("");

		btnVerwijderJa.setVisible(false);
		btnVerwijderNee.setVisible(false);
	}

	// CATEGORIE VERWIJDEREN CANCEL
	@FXML
	void verwijderCancel(ActionEvent event) {
		lblWaarschuwingen.setText("");

		btnVerwijderJa.setVisible(false);
		btnVerwijderNee.setVisible(false);
	}

	// CATEGORIN + SDG'S OPHALEN EN INLADEN
	public void getCategorieen() {
		List<Categorie> cat = d.getCategorieen();
		List<Categorie> catSorted = cat.stream().sorted(Comparator.comparing(Categorie::getNaam))
				.collect(Collectors.toList());
		categorieListView.getItems().addAll(catSorted);

		categorieListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Categorie>() {

			@Override
			public void changed(ObservableValue<? extends Categorie> arg0, Categorie arg1, Categorie arg2) {

				if (sdgListView.getItems() != null) {
					sdgListView.getItems().clear();
				}

				geselecteerdeCategorie = categorieListView.getSelectionModel().getSelectedItem();

				if (geselecteerdeCategorie != null) {
					lblCat.setText(geselecteerdeCategorie.toString());
					imgIcoon.setImage(new Image(geselecteerdeCategorie.getUrlIcoon()));
					getSdgs();
					enableButtons();
				} else {
					disableButtons();
				}

			}
		});
	}

	// SDG'S OPHALEN
	public void getSdgs() {
		List<Sdg> sdg = geselecteerdeCategorie.getSdgs();
		List<Sdg> sdgSorted = sdg.stream().sorted(Comparator.comparing(Sdg::getNummering)).collect(Collectors.toList());
		sdgListView.getItems().addAll(sdgSorted);
	}

	// SDG'S INLADEN
	public void sdgsOpvullen() {
		sdgList = d.getSdgs().stream().sorted(Comparator.comparing(Sdg::getNummering)).collect(Collectors.toList());
		sdgList.forEach(el -> cbSDG.getItems().add(el.getNaam()));
	}

	// SUBSDG'S OPHALEN + INLADEN
	public void subSdgsOpvullen(List<Sdg> newSdg) {
		cbSubSDG.getItems().clear();
		newSdg.forEach(el -> subSdgList.addAll(el.getSubSdg()));
		List<SubSdg> subSdgSorted = subSdgList.stream().sorted(Comparator.comparing(SubSdg::getNummering))
				.collect(Collectors.toList());
		subSdgSorted.forEach(el -> cbSubSDG.getItems().add(el.getNummering()));

		subSdgList.clear();
		subSdgSorted.clear();
	}

	// CATEGORIE AANMAKEN
	private void maakCategorie(String naam, String icoon, List<Sdg> sdgList, List<SubSdg> subSdgList) {
		d.insertCategorie(999, naam, icoon, sdgList, subSdgList);
	}

	// CATEGORIE UPDATEN
	private void updateCategorie(String naam, String icoon, List<Sdg> sdgList, List<SubSdg> subSdgList) {
		d.updateCategorie(geselecteerdeCategorie.getId(), naam, icoon, sdgList, subSdgList);
	}

	// URL CHECK
	private boolean urlCheck(String url) {
		return url.startsWith("https");
	}

	// CATEGORIE TOEVOEGEN/AANPASSEN COMPONENTEN TONEN
	private void categorieDetails(boolean visibility) {
		btnAnnuleren.setVisible(visibility);
		btnToevoegen.setVisible(visibility);
		btnNewCat.setVisible(!visibility);
		lblNaam.setVisible(visibility);
		txfNaam.setVisible(visibility);
		lblIcoon.setVisible(visibility);
		txfIcoon.setVisible(visibility);
		lblSdg.setVisible(visibility);
		cbSDG.setVisible(visibility);
		lblSubSdg.setVisible(visibility);
		cbSubSDG.setVisible(visibility);
	}

	// CATEGORIE INFO COMPONENTEN TONEN
	private void categorieGlobaal(boolean visibility) {
		lblCat.setVisible(visibility);
		btnDelete.setVisible(visibility);
		btnEdit.setVisible(visibility);
		sdgListView.setVisible(visibility);
		btnNewCat.setVisible(!visibility);
	}

	// BEWERK + TOEVOEGEN KNOP ENABLE
	private void enableButtons() {
		btnEdit.setDisable(false);
		btnDelete.setDisable(false);
	}

	// BEWERK + TOEVOEGEN KNOP DISABLE
	private void disableButtons() {
		btnEdit.setDisable(true);
		btnDelete.setDisable(true);
	}

}