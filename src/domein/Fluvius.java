package domein;

import java.util.List;
import java.util.Map;

import javafx.scene.chart.XYChart;
import repository.GenericDaoJPA;

public class Fluvius {

	private GenericDaoJPA<Categorie> catDao;
	private GenericDaoJPA<Sdg> sdgDao;
	private GenericDaoJPA<SubSdg> subSdgDao;
	private GenericDaoJPA<MvoDoelstelling> mvoDao;
	private GenericDaoJPA<SubMvoDoelstelling> subMvoDao;
	private GenericDaoJPA<Datasource> dataSDao;

	private MockData mockData;

	public Fluvius() {
		catDao = new GenericDaoJPA<>(Categorie.class);
		sdgDao = new GenericDaoJPA<>(Sdg.class);
		subSdgDao = new GenericDaoJPA<>(SubSdg.class);
		this.mvoDao = new GenericDaoJPA<>(MvoDoelstelling.class);
		this.subMvoDao = new GenericDaoJPA<>(SubMvoDoelstelling.class);
		dataSDao = new GenericDaoJPA<>(Datasource.class);
		mockData = new MockData();
	}

	// Categorie

	public List<Categorie> getCategorieen() {
		return catDao.findAll();
	}

	public Categorie getCategorieById(long catId) {
		return catDao.get(catId);
	}

	public void verwijderCategorie(long id) {
		GenericDaoJPA.startTransaction();
		catDao.delete(getCategorieById(id));
		GenericDaoJPA.commitTransaction();
	}

	public void updateCategorieNaam(long id, String naam) {
		GenericDaoJPA.startTransaction();
		getCategorieById(id).setNaam(naam);
		GenericDaoJPA.commitTransaction();
	}

	public void updateCategorieIcoon(long id, String icoon) {
		GenericDaoJPA.startTransaction();
		getCategorieById(id).setUrlIcoon(icoon);
		GenericDaoJPA.commitTransaction();
	}

	public void updateCategorieSdgs(long id, List<Sdg> sdgList) {
		GenericDaoJPA.startTransaction();
		getCategorieById(id).setSdgs(sdgList);
		GenericDaoJPA.commitTransaction();
	}

	public void updateCategorieSubSdgs(long id, List<SubSdg> subSdgList) {
		GenericDaoJPA.startTransaction();
		getCategorieById(id).setSubSdgs(subSdgList);
		GenericDaoJPA.commitTransaction();
	}

	public void insertCategorie(int id, String naam, String icoon, List<Sdg> sdgList, List<SubSdg> subSdgList) {
		GenericDaoJPA.startTransaction();
		catDao.insert(new Categorie(id, naam, icoon, sdgList, subSdgList));
		GenericDaoJPA.commitTransaction();
	}

	// SDG

	public List<Sdg> getSdgs() {
		return sdgDao.findAll();
	}

	public Sdg getSdgByName(String name) {
		return sdgDao.findByName(name);
	}

	// SUB_SDG

	public SubSdg getSubSdgById(long id) {
		return subSdgDao.get(id);
	}

	public SubSdg getSubSdgByNummering(String nummering) {
		return subSdgDao.findByNummering(nummering);
	}

	public void updateSubSdg(long id, List<MvoDoelstellingComponent> mvoDoelstellingen) {
		GenericDaoJPA.startTransaction();
		getSubSdgById(id).setMvoDoelstellingen(mvoDoelstellingen);
		GenericDaoJPA.commitTransaction();
	}

	// Datasources

	public List<Datasource> getDatasources() {
		return dataSDao.findAll();
	}

	public Datasource getDatasourceByName(String naam) {
		return dataSDao.findByName(naam);
	}

	public void insertDatasource(String naam, List<String> data) {
		GenericDaoJPA.startTransaction();
		dataSDao.insert(new Datasource(naam, data));
		GenericDaoJPA.commitTransaction();
	}

	public void verwijderDatasource(String naam) {
		GenericDaoJPA.startTransaction();
		dataSDao.delete(getDatasourceByName(naam));
		GenericDaoJPA.commitTransaction();
	}

	public void updateDatasourceNaam(String naam, String nieuweNaam) {
		GenericDaoJPA.startTransaction();
		getDatasourceByName(naam).setNaam(nieuweNaam);
		GenericDaoJPA.commitTransaction();
	}

	public Map<String, Integer> getMockData() {
		return mockData.mockdata();
	}

	public XYChart.Series<String, Integer> chartData(int index) {
		return mockData.chartData(index);
	}

	public List<Double> getAggregatedData(String naam) {
		AggregatedData data = new AggregatedData(getDatasourceByName(naam));
		return data.getValues();
	}

	// MVO Doelstellingen

	public List<MvoDoelstelling> getAllMvoDoelstellingen() {
		return mvoDao.findAll();
	}

	public MvoDoelstelling getDoelstellingById(long id) {
		return mvoDao.get(id);
	}

	public MvoDoelstelling getMvoDoelstellingByName(String naam) {
		return mvoDao.findByName(naam);
	}

	public void verwijderMvoDoelstelling(String naam) {
		GenericDaoJPA.startTransaction();
		mvoDao.delete(getMvoDoelstellingByName(naam));
		GenericDaoJPA.commitTransaction();
	}

	public void bewerkDoelstellingNaam(long id, String naam) {
		GenericDaoJPA.startTransaction();
		getDoelstellingById(id).setNaam(naam);
		GenericDaoJPA.commitTransaction();

	}

	public void bewerkDoelstellingUrl(long id, String url) {
		GenericDaoJPA.startTransaction();
		getDoelstellingById(id).setIcoon(url);
		GenericDaoJPA.commitTransaction();

	}

	public void insertMvoDoelstelling(long id, String naam, String url, List<MvoDoelstellingComponent> doelstellingen) {
		GenericDaoJPA.startTransaction();
		mvoDao.insert(new MvoDoelstelling(id, naam, url, doelstellingen));
		GenericDaoJPA.commitTransaction();
	}

	public MvoDoelstellingComponent getSubDoelstellingByName(String naam) {
		return subMvoDao.findByName(naam);
	}

}