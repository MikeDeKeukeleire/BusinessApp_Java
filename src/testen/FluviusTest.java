package testen;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Categorie;
import domein.Datasource;
import domein.DomeinController;
import domein.Fluvius;
import domein.Sdg;

@ExtendWith(MockitoExtension.class)
class FluviusTest {

	@Mock
	private Fluvius dummy;

	@InjectMocks
	private DomeinController dc;

	private static final long ID = 1L;
	private static final String NAME = "naam";

	@ParameterizedTest
	@CsvSource({ "naam, icoon", "test, test", "AB D, ADD__f" })
	public void test_getCategorieById(String naam, String icoon) {
		Categorie categorie = new Categorie(ID, naam, icoon, null, null);

		Mockito.when(dummy.getCategorieById(ID)).thenReturn(categorie);

		Categorie cat = dc.getCategorieById(ID);

		Assertions.assertEquals(cat, categorie);

		Mockito.verify(dummy).getCategorieById(ID);
	}

	@Test
	public void test_getCategorieen() {
		List<Categorie> categories = new ArrayList<>();

		categories.add(new Categorie(ID, "naam", "icoon", null, null));

		Mockito.when(dummy.getCategorieen()).thenReturn(categories);

		List<Categorie> list = dc.getCategorieen();

		Assertions.assertEquals(list, categories);

		Mockito.verify(dummy).getCategorieen();
	}

	@Test
	public void test_getSdgs() {
		List<Sdg> sdgs = new ArrayList<>();

		sdgs.add(new Sdg(ID, 1, "naam", "icoon", null));

		Mockito.when(dummy.getSdgs()).thenReturn(sdgs);

		List<Sdg> list = dc.getSdgs();

		Assertions.assertEquals(list, sdgs);

		Mockito.verify(dummy).getSdgs();
	}

	@Test
	public void test_getSdgByName() {
		Sdg sdg = new Sdg(ID, 1, NAME, "icoon", null);

		Mockito.when(dummy.getSdgByName(NAME)).thenReturn(sdg);

		Sdg result = dc.getSdgByName(NAME);

		Assertions.assertEquals(result, sdg);

		Mockito.verify(dummy).getSdgByName(NAME);
	}

	@Test
	public void test_getDatasources() {
		List<Datasource> datasource = new ArrayList<>();
		List<String> datas = new ArrayList<>();

		datas.add("ABC");
		datasource.add(new Datasource("naam", datas));

		Mockito.when(dummy.getDatasources()).thenReturn(datasource);

		List<Datasource> list = dc.getDatasources();

		Assertions.assertEquals(list, datasource);

		Mockito.verify(dummy).getDatasources();
	}

	@Test
	public void test_getDatasourceByName() {
		List<String> datas = new ArrayList<>();

		datas.add("ABC");

		Datasource datasource = new Datasource("naam", datas);

		Mockito.when(dummy.getDatasourceByName(NAME)).thenReturn(datasource);

		Assertions.assertEquals(dc.getDataVanDatasource(NAME), datasource);

		Mockito.verify(dummy).getDatasourceByName(NAME);
	}
}
