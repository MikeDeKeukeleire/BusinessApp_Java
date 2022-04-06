package domein;

import java.util.List;
import java.util.Map;

import javafx.scene.chart.XYChart;

public class DomeinController {

	private Fluvius fluvius;

	public DomeinController() {
		fluvius = new Fluvius();
	}

	// Categorie

	public List<Categorie> getCategorieen() {
		return fluvius.getCategorieen();
	}

	public Categorie getCategorieById(long catId) {
		return fluvius.getCategorieById(catId);
	}

	public void verwijderCategorie(long id) {
		fluvius.verwijderCategorie(id);
	}

	public void updateCategorie(long id, String naam, String url, List<Sdg> sdgList, List<SubSdg> subSdgList) {
		fluvius.updateCategorieNaam(id, naam);
		fluvius.updateCategorieIcoon(id, url);
		fluvius.updateCategorieSdgs(id, sdgList);
		fluvius.updateCategorieSubSdgs(id, subSdgList);
	}

	public void insertCategorie(int id, String naam, String icoon, List<Sdg> sdgList, List<SubSdg> subSdgList) {
		fluvius.insertCategorie(id, naam, icoon, sdgList, subSdgList);
	}

	// SDG

	public List<Sdg> getSdgs() {
		return fluvius.getSdgs();
	}

	public Sdg getSdgByName(String name) {
		return fluvius.getSdgByName(name);
	}

	// SUBSDG

	public SubSdg getSubSdgById(long id) {
		return fluvius.getSubSdgById(id);
	}

	public SubSdg getSubSdgByNummering(String nummering) {
		return fluvius.getSubSdgByNummering(nummering);
	}

	public void updateSubSdg(long id, List<MvoDoelstellingComponent> mvoDoelstellingen) {
		fluvius.updateSubSdg(id, mvoDoelstellingen);

	}

	// Datasources

	public List<Datasource> getDatasources() {
		return fluvius.getDatasources();
	}

	public Datasource getDataVanDatasource(String naam) {
		return fluvius.getDatasourceByName(naam);
	}

	public void insertDatasource(String naam, List<String> data) {
		fluvius.insertDatasource(naam, data);
	}

	public void verwijderDatasource(String geselecteerdeDatasource) {
		fluvius.verwijderDatasource(geselecteerdeDatasource);

	}

	public void updateDatasourceNaam(String naam, String nieuweNaam) {
		fluvius.updateDatasourceNaam(naam, nieuweNaam);
	}

	public List<Double> getAggregatedData(String naam) {
		return fluvius.getAggregatedData(naam);
	}

	// MVO Doelstellingen

	public List<MvoDoelstelling> getAllMvoDoelstellingen() {
		return fluvius.getAllMvoDoelstellingen();
	}

	public MvoDoelstelling getMvoDoelstellingByName(String naam) {
		return fluvius.getMvoDoelstellingByName(naam);
	}

	public void verwijderMvoDoelstelling(String naam) {
		fluvius.verwijderMvoDoelstelling(naam);
	}

	public Map<String, Integer> getMockData() {
		return fluvius.getMockData();
	}

	public XYChart.Series<String, Integer> chartData(int index) {
		return fluvius.chartData(index);
	}

	public void bewerkMvoDoelstelling(long id, String naam, String url) {
		fluvius.bewerkDoelstellingNaam(id, naam);
		fluvius.bewerkDoelstellingUrl(id, url);
	}

	public void voegMvoDoelstellingToe(long id, String naam, String url,
			List<MvoDoelstellingComponent> doelstellingen) {
		fluvius.insertMvoDoelstelling(id, naam, url, doelstellingen);

	}

	public MvoDoelstellingComponent getSubDoelstellingByName(String naam) {
		return fluvius.getSubDoelstellingByName(naam);
	}
}