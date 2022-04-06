package domein;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Subsdgs")
public class SubSdg implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nummering;
	private String beschrijving;
	private String urlIcoon;
	@JoinColumn(name = "SUBSDG")
	@OneToMany
	private List<MvoDoelstellingComponent> mvoDoelstellingen;

	public SubSdg(long id, String nummering, String beschrijving, String urlIcoon,
			List<MvoDoelstellingComponent> mvoDoelstellingen) {
		setNummering(nummering);
		setId(id);
		setBeschrijving(beschrijving);
		setUrlIcoon(urlIcoon);
		setMvoDoelstellingen(mvoDoelstellingen);
	}

	public SubSdg() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<MvoDoelstellingComponent> getMvoDoelstellingen() {
		return mvoDoelstellingen;
	}

	public void setMvoDoelstellingen(List<MvoDoelstellingComponent> mvoDoelstellingen) {
		this.mvoDoelstellingen = mvoDoelstellingen;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public String getUrlIcoon() {
		return urlIcoon;
	}

	public void setUrlIcoon(String urlIcoon) {
		this.urlIcoon = urlIcoon;
	}

	public String getNummering() {
		return nummering;
	}

	public void setNummering(String nummering) {
		this.nummering = nummering;
	}

	@Override
	public String toString() {
		return nummering;
	}

}