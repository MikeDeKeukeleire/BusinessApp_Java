package domein;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Gebruikers")
public class Gebruiker implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	private String wachtwoord;
	private String rol;

	public Gebruiker() {

	}

	public Gebruiker(long id, String naam, String wachtwoord, String rol) {
		setId(id);
		setGebruikersnaam(naam);
		setWachtwoord(wachtwoord);
		setRol(rol);
	}

	public String getGebruikersnaam() {
		return naam;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setGebruikersnaam(String naam) {
		this.naam = naam;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	@Override
	public String toString() {
		return "Gebruikersnaam:" + naam + " Wachtwoord:" + wachtwoord;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		result = prime * result + ((wachtwoord == null) ? 0 : wachtwoord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gebruiker other = (Gebruiker) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		if (wachtwoord == null) {
			if (other.wachtwoord != null)
				return false;
		} else if (!wachtwoord.equals(other.wachtwoord))
			return false;
		return true;
	}

}