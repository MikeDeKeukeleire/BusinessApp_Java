package domein;

import repository.GenericDao;
import repository.GenericDaoJPA;

public class LoginController {

	private GenericDao<Gebruiker> gebruikerRepo;
	private Gebruiker gebruiker;

	public LoginController() {
		// new PopulateDB().run();
		setGebruikerRepo(new GenericDaoJPA<>(Gebruiker.class));
	}

	public DomeinController login(String gebruikersnaam, String wachtwoord) {
		gebruiker = findGebruikerByName(gebruikersnaam);
		if (gebruiker != null) {
			if (controleerWachtwoord(gebruiker, wachtwoord) == true)
				return new DomeinController();
		}
		return null;
	}

	public Gebruiker findGebruikerByName(String gebruikersnaam) {
		return gebruikerRepo.findByName(gebruikersnaam);
	}

	public boolean controleerWachtwoord(Gebruiker gebruiker, String wachtwoord) {
		return gebruiker.getWachtwoord().equals(wachtwoord);
	}

	public void setGebruikerRepo(GenericDao<Gebruiker> mock) {
		gebruikerRepo = mock;
	}
}