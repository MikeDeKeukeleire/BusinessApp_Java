package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MVODoelstelling")
public class MvoDoelstelling extends MvoDoelstellingComponent implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToMany
	@JoinColumn(name = "subsMVOdoelstelling")
	private List<MvoDoelstellingComponent> mvoDoelstellingComponenten = new ArrayList<>();

	@Override
	public double getWaarde() {
		// TODO Formules van datasources nog toepassen
		return this.mvoDoelstellingComponenten.stream().mapToDouble(MvoDoelstellingComponent::getWaarde).sum();
	}

	@Override
	public double getDoelwaarde() {
		// TODO Formules van datasources toevoegen
		return this.mvoDoelstellingComponenten.stream().mapToDouble(MvoDoelstellingComponent::getDoelwaarde).sum();
	}

	public List<SubMvoDoelstelling> getSubMVODoelstellingen() {
		return this.mvoDoelstellingComponenten.stream().map(doelstelling -> (SubMvoDoelstelling) doelstelling)
				.collect(Collectors.toList());
	}

	@Override
	public void add(MvoDoelstellingComponent mvoDoelstellingComponent) {
		if (this.mvoDoelstellingComponenten.contains(mvoDoelstellingComponent))
			throw new IllegalArgumentException("De sub-MVO-doelstelling is reeeds verbonden aan deze doelstelling!");

		this.mvoDoelstellingComponenten.add(mvoDoelstellingComponent);
	}

	public void setMvoDoelstellingComponenten(List<MvoDoelstellingComponent> mvoDoelstellingComponenten) {
		this.mvoDoelstellingComponenten = mvoDoelstellingComponenten;
	}

	@Override
	public void remove(MvoDoelstellingComponent mvoDoelstellingComponent) {
		if (!this.mvoDoelstellingComponenten.contains(mvoDoelstellingComponent))
			throw new IllegalArgumentException("De MVO-doelstelling was niet verbonden aan deze doelstelling");

		this.mvoDoelstellingComponenten.remove(mvoDoelstellingComponent);
	}

	@Override
	public Iterator<MvoDoelstellingComponent> createIterator() {
		return this.mvoDoelstellingComponenten.iterator();
	}

	protected MvoDoelstelling() {
		// Voor JPA
	}

	public MvoDoelstelling(long id, String naam, String icoon, List<MvoDoelstellingComponent> doelstellingen) {
		this.setId(id);
		this.setNaam(naam);
		this.setIcoon(icoon);
		this.setMvoDoelstellingComponenten(doelstellingen);
	}
}