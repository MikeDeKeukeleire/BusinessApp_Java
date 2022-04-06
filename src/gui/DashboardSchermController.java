package gui;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import domein.Categorie;
import domein.DomeinController;
import domein.MvoDoelstellingComponent;
import domein.Sdg;
import domein.SubSdg;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class DashboardSchermController extends BorderPane {

	private DomeinController d;

	@FXML
	private Button voegSdgToe;

	@FXML
	private Button btnVoegMVOToe;

	@FXML
	private Button btnVoegCategorieToe;

	@FXML
	private ListView<Categorie> categorieListView;

	@FXML
	private ListView<MvoDoelstellingComponent> mvoDoelstellingenListView;

	@FXML
	private ListView<Sdg> sdgListView;

	@FXML
	private ListView<SubSdg> subSdgListView;

	private Categorie geselecteerdeCategorie;

	private Sdg geselecteerdeSdg;

	private SubSdg geselecteerdeSubSdg;

	@FXML
	private Label lblCategorie;

	@FXML
	private Label lblSdg;

	@FXML
	private Label lblSubSdg;

	@FXML
	private Label lblMvoDoelstelling;

	@FXML
	private AreaChart<String, Integer> arcData;

	public DashboardSchermController(DomeinController d) {
		this.d = d;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardScherm.fxml"));
		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		getCategorieen();

	}

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
				lblCategorie.setText(geselecteerdeCategorie.toString());

				getSdgs();

			}
		});
	}

	public void getSdgs() {
		List<Sdg> sdg = geselecteerdeCategorie.getSdgs();
		List<Sdg> sdgSorted = sdg.stream().sorted(Comparator.comparing(Sdg::getNummering)).collect(Collectors.toList());
		sdgListView.getItems().addAll(sdgSorted);

		sdgListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Sdg>() {

			@Override
			public void changed(ObservableValue<? extends Sdg> arg0, Sdg arg1, Sdg arg2) {

				if (subSdgListView.getItems() != null) {
					subSdgListView.getItems().clear();
				}

				geselecteerdeSdg = sdgListView.getSelectionModel().getSelectedItem();
				if (geselecteerdeSdg != null) {
					lblSdg.setText(geselecteerdeSdg.toString());
					getSubSdgs();
				}

			}

		});

	}

	public void getSubSdgs() {

		subSdgListView.getItems().addAll(geselecteerdeSdg.getSubSdg().stream()
				.sorted(Comparator.comparing(SubSdg::getNummering)).collect(Collectors.toList()));

		subSdgListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SubSdg>() {

			@Override
			public void changed(ObservableValue<? extends SubSdg> arg0, SubSdg arg1, SubSdg arg2) {

				if (mvoDoelstellingenListView.getItems() != null) {
					mvoDoelstellingenListView.getItems().clear();
				}

				geselecteerdeSubSdg = subSdgListView.getSelectionModel().getSelectedItem();
				if (geselecteerdeSubSdg != null) {
					lblSubSdg.setText("Sustainable Dev goal " + geselecteerdeSubSdg.toString());
					getMvoDoelstellingen();
				}
			}

		});
	}

	public void getMvoDoelstellingen() {
		mvoDoelstellingenListView.getItems().addAll(geselecteerdeSubSdg.getMvoDoelstellingen());

		mvoDoelstellingenListView.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<MvoDoelstellingComponent>() {

					@Override
					public void changed(ObservableValue<? extends MvoDoelstellingComponent> arg0,
							MvoDoelstellingComponent arg1, MvoDoelstellingComponent arg2) {

						MvoDoelstellingComponent geselecteerdeMvoDoelstelling = mvoDoelstellingenListView
								.getSelectionModel().getSelectedItem();
						if (geselecteerdeMvoDoelstelling != null) {
							lblMvoDoelstelling.setText(geselecteerdeMvoDoelstelling.toString());
						}
					}
				});
	}

	@FXML
	public void mvoItemOnMouseClicked(MouseEvent event) {
		arcData.getData().clear();
		
		XYChart.Series<String, Integer> series;

		int index = mvoDoelstellingenListView.getSelectionModel().getSelectedIndex();
		
		series = d.chartData(index);
		arcData.getData().add(series);				
	}
}
