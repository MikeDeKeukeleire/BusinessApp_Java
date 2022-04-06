package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Categorie;

class CategorieTest {

	private Categorie categorie;
	
	private static final long ID = 1L;
	private static final String NAAM = "naam";
	private static final String ICOON = "icoon";	
	
	@BeforeEach
	public void before() {
		categorie = new Categorie(ID, NAAM, ICOON, null, null);
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	public void test_setNaam_Exception(String naam) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> categorie.setNaam(naam));
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	public void test_setIcoon_Exception(String icoon) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> categorie.setUrlIcoon(icoon));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"naam", "abc", "123456", "  AS ", "Ab cD ", "ac1 6b N2/ "})
	public void test_setNaam_NoException(String naam) {
		categorie.setNaam(naam);
		Assertions.assertEquals(naam, categorie.getNaam());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"icoon", "abc", "123456", "  AS ", "Ab cD ", "ac1 6b N2/ "})
	public void test_setIcoon_NoException(String icoon) {
		categorie.setUrlIcoon(icoon);
		Assertions.assertEquals(icoon, categorie.getUrlIcoon());
	}

}
