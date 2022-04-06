package domein;

import java.util.ArrayList;
import java.util.List;

public class AggregatedData {

	private Datasource datasource;
	private List<String> rawData;
	private List<Double> aggregatedData;
	private String typeData;

	public AggregatedData(Datasource datasource) {
		this.datasource = datasource;
		rawData = datasource.getData();
		aggregatedData = new ArrayList<>();
	}

	public List<Double> getValues() {
		String item = "";
		for (int i = 0; i < rawData.size(); i++) {
			String[] row = rawData.get(i).split(";");
			if (i == 0)
				typeData = item;
			else {
				item = row[row.length - 1];
				aggregatedData.add(Double.parseDouble(item));
			}
		}
		return aggregatedData;
	}

	public String getTypeData() {
		return typeData;
	}

	public double getGemiddelde() {
		return getValues().stream().mapToDouble(Double::doubleValue).average().orElse(-1);
	}

	public double getGrootsteWaarde() {
		return getValues().stream().mapToDouble(Double::doubleValue).max().orElse(-1);
	}

	public double getKleinsteWaarde() {
		return getValues().stream().mapToDouble(Double::doubleValue).min().orElse(-1);
	}

	@Override
	public String toString() {
		return this.datasource.getNaam();
	}
}