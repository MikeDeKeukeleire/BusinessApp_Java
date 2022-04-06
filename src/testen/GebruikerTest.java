package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Gebruiker;
import domein.LoginController;

class GebruikerTest {
	
	private LoginController controller = new LoginController();
	
	private Gebruiker gebruiker;
	
	@BeforeEach
	public void before() {
		gebruiker = new Gebruiker(1, "coordinator", "Test123", "MvoCoordinator");
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Test123"})
	public void test_controleerWachtwoord(String wachtwoord) {
		Assertions.assertTrue(controller.controleerWachtwoord(gebruiker, wachtwoord));
	}
}
