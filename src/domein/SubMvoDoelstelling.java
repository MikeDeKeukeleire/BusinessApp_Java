package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subMVODoelstelling")
public class SubMvoDoelstelling extends MvoDoelstellingComponent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double doelwaarde;
	private List<AggregatedData> datasources = new ArrayList<>();

	@Override
	public double getWaarde() {
		// TODO Formules van datasources nog toepassen
		return this.datasources.stream().mapToDouble(AggregatedData::getGemiddelde).sum();
	}

	@Override
	public double getDoelwaarde() {
		return this.doelwaarde;
	}

	public void setDoelwaarde(double doelwaarde) {
		this.doelwaarde = doelwaarde;
	}

	public List<AggregatedData> getDatasources() {
		return this.datasources;
	}

	public void add(AggregatedData datasource) {
		if (this.datasources.contains(datasource))
			throw new IllegalArgumentException("De datasource is reeeds verbonden aan deze doelstelling!");

		this.datasources.add(datasource);
	}

	private void setDatasources(ArrayList<AggregatedData> datasources) {
		if (datasources == null || datasources.size() == 0)
			datasources = new ArrayList<>();

		this.datasources = datasources;
	}

	public void remove(AggregatedData datasource) {
		if (!this.datasources.contains(datasource))
			throw new IllegalArgumentException("De datasource was niet verbonden aan deze doelstelling");

		this.datasources.remove(datasource);
	}

	@Override
	public Iterator<MvoDoelstellingComponent> createIterator() {
		return new MvoDoelstellingNullIterator();
	}

	protected SubMvoDoelstelling() {
		// Voor JPA
	}

	public SubMvoDoelstelling(long id, String naam, String icoon, ArrayList<AggregatedData> datasources) {
		this.setId(id);
		this.setNaam(naam);
		this.setIcoon(icoon);
		this.setDatasources(datasources);
	}
}