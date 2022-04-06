package domein;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@EntityListeners(DatasourceListener.class)
@Table(name = "Datasources")
public class Datasource implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String naam;
	private List<String> data;

	public Datasource(String naam, List<String> data) {
		setNaam(naam);
		setData(data);
	}

	protected Datasource() {
		// default constructor voor JPA
	}

	public void setNaam(String naam) {
		if (naam == null || naam.isBlank() || naam.isEmpty() || naam.length() <= 2)
			throw new IllegalArgumentException("Naam moet minstens 2 karakters hebben");
		this.naam = naam;
	}

	public void setData(List<String> data) {
		if (data == null || data.isEmpty())
			throw new IllegalArgumentException("Data mag niet null of leeg zijn");
		this.data = data;

	}

	public String getNaam() {
		return naam;
	}

	public List<String> getData() {
		return data;
	}

	@Override
	public String toString() {
		return String.format("Naam: %s | Data: %s", naam, data);
	}
}