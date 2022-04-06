package domein;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@EntityListeners(CategorieListener.class)
@Table(name = "Categorieen")
public class Categorie implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	private String urlIcoon;
	@JoinColumn(name = "CATEGORIE")
	@OneToMany
	private List<Sdg> sdgs;
	@JoinColumn(name = "CATEGORIE")
	@OneToMany
	private List<SubSdg> subSdgs;

	public Categorie(long id, String naam, String urlIcoon, List<Sdg> sdgs, List<SubSdg> subSdgs) {
		setId(id);
		setNaam(naam);
		setUrlIcoon(urlIcoon);
		setSdgs(sdgs);
		setSubSdgs(subSdgs);
	}

	public Categorie() {

	}

	public boolean removeCategorie(int count) {
		if (count > 1)
			return true;
		else
			return false;
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public List<Sdg> getSdgs() {
		return sdgs;
	}

	public List<SubSdg> getSubSdgs() {
		return subSdgs;
	}

	public String getUrlIcoon() {
		return urlIcoon;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNaam(String naam) {
		if (naam == null || naam.trim().isEmpty())
			throw new IllegalArgumentException();
		this.naam = naam;
	}

	public void setSdgs(List<Sdg> sdgs) {
		this.sdgs = sdgs;
	}

	public void setSubSdgs(List<SubSdg> subSdgs) {
		this.subSdgs = subSdgs;
	}

	public void setUrlIcoon(String urlIcoon) {
		if (urlIcoon == null || urlIcoon.trim().isBlank())
			throw new IllegalArgumentException();
		this.urlIcoon = urlIcoon;
	}

	@Override
	public String toString() {
		return naam;
	}

}