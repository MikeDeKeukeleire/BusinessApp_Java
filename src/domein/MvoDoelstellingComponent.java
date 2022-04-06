package domein;

import java.util.Iterator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MVODoelstellingComponent")
public abstract class MvoDoelstellingComponent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	private String icoon;

	public String getNaam() {
		return this.naam;
	}

	public String getIcoon() {
		return this.icoon;
	}

	public long getId() {
		return this.id;
	}

	public void setNaam(String naam) {
		if (naam == null || naam.length() == 0)
			throw new IllegalArgumentException("De naam mag niet leeg zijn");

		this.naam = naam;
	}

	public void setIcoon(String icoon) {
		if (icoon == null || icoon.length() == 0) {
			this.icoon = "https://cdn.worldvectorlogo.com/logos/red-star-1.svg";
			return;
		}

		this.icoon = icoon;
	}

	protected void setId(long id) {
		this.id = id;
	}

	public abstract double getWaarde();

	public abstract double getDoelwaarde();

	public void add(MvoDoelstellingComponent component) {
		throw new UnsupportedOperationException();
	}

	public void remove(MvoDoelstellingComponent component) {
		throw new UnsupportedOperationException();
	}

	public abstract Iterator<MvoDoelstellingComponent> createIterator();

	@Override
	public String toString() {
		return this.getNaam();
	}

	protected MvoDoelstellingComponent() {

	}
}