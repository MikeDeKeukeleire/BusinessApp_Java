package domein;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Sdgs")
public class Sdg implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int nummering;
	private String naam;
	private String urlIcoon;
	@JoinColumn(name = "SDG")
	@OneToMany
	private List<SubSdg> subSdg;
	@Transient
	private String namen[] = { "no poverty", "zero hunger", "good health and well-being", "quality education",
			"gender equality", "clean water and sanitation", "affordable and clean energy",
			"decent work and economic growth", "industry, innovation and infrastructure", "reduce inequalities",
			"sustainable cities and communities", "responsible consumption and production", "climate action",
			"life below water", "life on land", "peace, justice and string institutions",
			"partnerships for the goals" };

	public Sdg(long id, int nummering, String naam, String urlIcoon, List<SubSdg> subSdg) {
		setNummering(nummering);
		setId(id);
		setNaam(naam);
		setUrlIcoon(urlIcoon);
		setSubSdg(subSdg);
	}

	public Sdg() {

	}

	public void setId(long id) {
		this.id = id;
	};

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public String getUrlIcoon() {
		return urlIcoon;
	}

	public List<SubSdg> getSubSdg() {
		return subSdg;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setUrlIcoon(String urlIcoon) {
		this.urlIcoon = urlIcoon;
	}

	public void setSubSdg(List<SubSdg> subSdg) {
		this.subSdg = subSdg;
	}

	public int getNummering() {
		return nummering;
	}

	public void setNummering(int nummering) {
		this.nummering = nummering;
	}

	@Override
	public String toString() {
		return nummering + " " + naam;
	}

	@Override
	public int hashCode() {
		return Objects.hash(naam);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sdg other = (Sdg) obj;
		return Objects.equals(naam, other.naam);
	}
}