package testen;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Datasource;

class DatasourceTest {

	private Datasource datasource;

	@BeforeEach
	public void before() {
		datasource = new Datasource("naam", list());
	}

	@ParameterizedTest
	@NullAndEmptySource
	public void test_setNaam_Exception(String naam) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> datasource.setNaam(naam));
	}

	@ParameterizedTest
	@ValueSource(strings = { "naam", "abc", "123456", "  AS ", "Ab cD ", "ac1 6b N2/ " })
	public void test_setNaam_NoException(String naam) {
		datasource.setNaam(naam);
		Assertions.assertEquals(naam, datasource.getNaam());
	}

	@ParameterizedTest
	@NullAndEmptySource
	public void test_setData_Exception(List<String> data) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> datasource.setData(data));
	}

	@Test
	public void test_setData_NoException() {
		List<String> list = list();
		datasource.setData(list());
		Assertions.assertEquals(list, datasource.getData());
	}

	private List<String> list() {
		List<String> list = new ArrayList<>();

		String item1 = "ABC";

		list.add(item1);

		return list;
	}

}
