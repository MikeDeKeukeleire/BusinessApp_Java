package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.controlsfx.control.CheckComboBox;

import domein.DomeinController;
import domein.MvoDoelstelling;
import domein.MvoDoelstellingComponent;
import domein.Sdg;
import domein.SubSdg;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class SdgSchermController extends BorderPane {

	private DomeinController d;
	@FXML
	private ListView<Sdg> sdgListView;
	@FXML
	private Label lblSubSdg;
	@FXML
	private ListView<SubSdg> subSdgListView;
	@FXML
	private ListView<MvoDoelstellingComponent> mvoDoelstellingenListView;
	@FXML
	private TextArea txtaBeschrijving;
	@FXML
	private Button btnSdgAanpassen;
	@FXML
	private Button btnSubmit;
	@FXML
	private Button btnCancel;
	@FXML
	private CheckComboBox<String> cbMvoDoelstellingen;
	@FXML
	private Label labelSelectDoelstellingen;
	@FXML
	private Label lblSelecteerMvoDoelstelling;

	private List<MvoDoelstelling> mvoDoelstellingenList;

	private Sdg geselecteerdeSdg;

	private SubSdg geselecteerdeSubSdg;

	public SdgSchermController(DomeinController d) {
		this.d = d;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("SdgScherm.fxml"));
		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		getSdgs();
		lblSelecteerMvoDoelstelling.setVisible(false);
		btnSdgAanpassen.setDisable(true);
		sdgDetails(false);
	}

	@FXML
	public void sdgAanpassen(ActionEvent event) {
		if (geselecteerdeSubSdg != null) {
			sdgGlobaal(false);
			sdgDetails(true);
		} else {
			System.out.println("selecteer een Sdg en Sub Sdg");
		}
	}

	@FXML
	public void cancel(ActionEvent event) {
		cbMvoDoelstellingen.getCheckModel().clearChecks();
		sdgGlobaal(true);
		sdgDetails(false);
	}

	@FXML
	public void submit(ActionEvent event) {
		lblSelecteerMvoDoelstelling.setVisible(false);
		List<MvoDoelstellingComponent> newMvoDoelstellingen = new ArrayList<>();
		if (!cbMvoDoelstellingen.getCheckModel().getCheckedItems().isEmpty()) {

			cbMvoDoelstellingen.getCheckModel().getCheckedItems()
					.forEach(el -> newMvoDoelstellingen.add(d.getMvoDoelstellingByName(el)));
			d.updateSubSdg(geselecteerdeSubSdg.getId(), newMvoDoelstellingen);

			cbMvoDoelstellingen.getCheckModel().clearChecks();
			sdgListView.getItems().clear();
			btnSdgAanpassen.setDisable(true);
			sdgGlobaal(true);
			sdgDetails(false);
			getSdgs();

		} else {
			lblSelecteerMvoDoelstelling.setVisible(true);
		}

	}

	public void getSdgs() {
		List<Sdg> sdg = d.getSdgs();
		List<Sdg> sdgSorted = sdg.stream().sorted(Comparator.comparing(Sdg::getNummering)).collect(Collectors.toList());
		sdgListView.getItems().addAll(sdgSorted);

		sdgListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Sdg>() {

			@Override
			public void changed(ObservableValue<? extends Sdg> arg0, Sdg arg1, Sdg arg2) {

				if (subSdgListView.getItems() != null) {
					subSdgListView.getItems().clear();
				}

				geselecteerdeSdg = (Sdg) sdgListView.getSelectionModel().getSelectedItem();
				if (geselecteerdeSdg != null) {
					getSubSdgs();
				}
			}
		});

	}

	public void getSubSdgs() {
		List<SubSdg> subSdg = geselecteerdeSdg.getSubSdg();
		List<SubSdg> subSdgSorted = subSdg.stream().sorted(Comparator.comparing(SubSdg::getNummering))
				.collect(Collectors.toList());
		subSdgListView.getItems().addAll(subSdgSorted);

		subSdgListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SubSdg>() {

			@Override
			public void changed(ObservableValue<? extends SubSdg> arg0, SubSdg arg1, SubSdg arg2) {

				if (mvoDoelstellingenListView.getItems() != null) {
					mvoDoelstellingenListView.getItems().clear();
				}

				geselecteerdeSubSdg = subSdgListView.getSelectionModel().getSelectedItem();
				if (geselecteerdeSubSdg != null) {
					btnSdgAanpassen.setDisable(false);
					lblSubSdg.setText("Sustainable Dev goal " + String.valueOf(geselecteerdeSdg.getId()) + "."
							+ geselecteerdeSubSdg.toString());
					txtaBeschrijving.setText(geselecteerdeSubSdg.getBeschrijving());
					getMvoDoelstellingen();
				}
			}

		});

	}

	public void getMvoDoelstellingen() {
		mvoDoelstellingenListView.getItems().addAll(geselecteerdeSubSdg.getMvoDoelstellingen());

		cbMvoDoelstellingen.getItems().clear();
		mvoDoelstellingenList = d.getAllMvoDoelstellingen();
		mvoDoelstellingenList.forEach(el -> cbMvoDoelstellingen.getItems().add(el.toString()));

		mvoDoelstellingenListView.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<MvoDoelstellingComponent>() {

					@Override
					public void changed(ObservableValue<? extends MvoDoelstellingComponent> arg0,
							MvoDoelstellingComponent arg1, MvoDoelstellingComponent arg2) {

						MvoDoelstellingComponent geselecteerdeMvoDoelstelling = mvoDoelstellingenListView
								.getSelectionModel().getSelectedItem();
					}
				});
	}

	public void sdgGlobaal(boolean visibility) {
		mvoDoelstellingenListView.setVisible(visibility);
		btnSdgAanpassen.setVisible(visibility);
	}

	public void sdgDetails(boolean visibility) {
		btnSubmit.setVisible(visibility);
		btnCancel.setVisible(visibility);
		cbMvoDoelstellingen.setVisible(visibility);
		labelSelectDoelstellingen.setVisible(visibility);
	}

}
