package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.MvoDoelstelling;

class MvoDoelstellingTest {

	private MvoDoelstelling doelstelling;

	@BeforeEach
	public void before() {
		doelstelling = new MvoDoelstelling(1, "naam", "icoon", null);
	}

	@ParameterizedTest
	@NullAndEmptySource
	public void test_setNaam_Excpetion(String naam) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> doelstelling.setNaam(naam));
	}

	@ParameterizedTest
	@ValueSource(strings = { "naam", "abc", "123456", "  AS ", "Ab cD ", "ac1 6b N2/ " })
	public void test_setNaam_NoException(String naam) {
		doelstelling.setNaam(naam);
		Assertions.assertEquals(naam, doelstelling.getNaam());
	}

	@ParameterizedTest
	@ValueSource(strings = { "naam", "abc", "123456", "  AS ", "Ab cD ", "ac1 6b N2/ " })
	public void test_setIcoon_NoException(String icoon) {
		doelstelling.setIcoon(icoon);
		Assertions.assertEquals(icoon, doelstelling.getIcoon());
	}
}
