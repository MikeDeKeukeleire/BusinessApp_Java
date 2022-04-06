package domein;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class MockData {

	public Map<String, Integer> mockdata() {

		HashMap<String, Integer> map = new HashMap<>();

		map.put("Jan", 23);
		map.put("Feb", 29);
		map.put("Mar", 31);
		map.put("Apr", 30);
		map.put("Mei", 26);
		map.put("Jun", 27);
		map.put("Jul", 24);
		map.put("Aug", 34);
		map.put("Sep", 33);
		map.put("Okt", 20);
		map.put("Nov", 24);
		map.put("Dec", 27);

		return map;
	}
	
	public XYChart.Series<String, Integer> chartData(int index) {
		switch (index) {
		case 0: return mockData1Chart();
		case 1: return mockData2Chart();
		case 2: return mockData3Chart();
		case 3: return mockData4Chart();
		case 4: return mockData5Chart();
		default: return legeSeries();
		}
		
	}

	private XYChart.Series<String, Integer> mockData1Chart() {
		XYChart.Series<String, Integer> series = new XYChart.Series<>();

		series.getData().add(new XYChart.Data<String, Integer>("Januari", 100));
		series.getData().add(new XYChart.Data<String, Integer>("Februari", 75));
		series.getData().add(new XYChart.Data<String, Integer>("Maart", 33));
		series.getData().add(new XYChart.Data<String, Integer>("April", 67));
		series.getData().add(new XYChart.Data<String, Integer>("Mei", 98));
		series.getData().add(new XYChart.Data<String, Integer>("Juni", 56));
		series.getData().add(new XYChart.Data<String, Integer>("Juli", 78));
		series.getData().add(new XYChart.Data<String, Integer>("Augustus", 89));
		series.getData().add(new XYChart.Data<String, Integer>("September", 35));
		series.getData().add(new XYChart.Data<String, Integer>("Oktober", 47));
		series.getData().add(new XYChart.Data<String, Integer>("November", 91));
		series.getData().add(new XYChart.Data<String, Integer>("December", 15));

		return series;
	}

	private  Series<String, Integer> mockData2Chart() {
		XYChart.Series<String, Integer> series = new XYChart.Series<>();

		series.getData().add(new XYChart.Data<String, Integer>("Januari", 70));
		series.getData().add(new XYChart.Data<String, Integer>("Februari", 85));
		series.getData().add(new XYChart.Data<String, Integer>("Maart", 13));
		series.getData().add(new XYChart.Data<String, Integer>("April", 87));
		series.getData().add(new XYChart.Data<String, Integer>("Mei", 78));
		series.getData().add(new XYChart.Data<String, Integer>("Juni", 46));
		series.getData().add(new XYChart.Data<String, Integer>("Juli", 68));
		series.getData().add(new XYChart.Data<String, Integer>("Augustus", 99));
		series.getData().add(new XYChart.Data<String, Integer>("September", 85));
		series.getData().add(new XYChart.Data<String, Integer>("Oktober", 67));
		series.getData().add(new XYChart.Data<String, Integer>("November", 71));
		series.getData().add(new XYChart.Data<String, Integer>("December", 35));

		return series;
	}
	
	private Series<String, Integer> mockData3Chart() {
		XYChart.Series<String, Integer> series = new XYChart.Series<>();

		series.getData().add(new XYChart.Data<String, Integer>("Januari", 35));
		series.getData().add(new XYChart.Data<String, Integer>("Februari", 56));
		series.getData().add(new XYChart.Data<String, Integer>("Maart", 28));
		series.getData().add(new XYChart.Data<String, Integer>("April", 56));
		series.getData().add(new XYChart.Data<String, Integer>("Mei", 51));
		series.getData().add(new XYChart.Data<String, Integer>("Juni", 46));
		series.getData().add(new XYChart.Data<String, Integer>("Juli", 96));
		series.getData().add(new XYChart.Data<String, Integer>("Augustus", 56));
		series.getData().add(new XYChart.Data<String, Integer>("September", 67));
		series.getData().add(new XYChart.Data<String, Integer>("Oktober", 89));
		series.getData().add(new XYChart.Data<String, Integer>("November", 47));
		series.getData().add(new XYChart.Data<String, Integer>("December", 58));

		return series;
	}
	
	private Series<String, Integer> mockData4Chart() {
		XYChart.Series<String, Integer> series = new XYChart.Series<>();

		series.getData().add(new XYChart.Data<String, Integer>("Januari", 36));
		series.getData().add(new XYChart.Data<String, Integer>("Februari", 47));
		series.getData().add(new XYChart.Data<String, Integer>("Maart", 27));
		series.getData().add(new XYChart.Data<String, Integer>("April", 31));
		series.getData().add(new XYChart.Data<String, Integer>("Mei", 42));
		series.getData().add(new XYChart.Data<String, Integer>("Juni", 55));
		series.getData().add(new XYChart.Data<String, Integer>("Juli", 53));
		series.getData().add(new XYChart.Data<String, Integer>("Augustus", 63));
		series.getData().add(new XYChart.Data<String, Integer>("September", 81));
		series.getData().add(new XYChart.Data<String, Integer>("Oktober", 76));
		series.getData().add(new XYChart.Data<String, Integer>("November", 78));
		series.getData().add(new XYChart.Data<String, Integer>("December", 98));

		return series;
	}
	
	private Series<String, Integer> mockData5Chart() {
		XYChart.Series<String, Integer> series = new XYChart.Series<>();

		series.getData().add(new XYChart.Data<String, Integer>("Januari", 87));
		series.getData().add(new XYChart.Data<String, Integer>("Februari", 77));
		series.getData().add(new XYChart.Data<String, Integer>("Maart", 69));
		series.getData().add(new XYChart.Data<String, Integer>("April", 87));
		series.getData().add(new XYChart.Data<String, Integer>("Mei", 56));
		series.getData().add(new XYChart.Data<String, Integer>("Juni", 75));
		series.getData().add(new XYChart.Data<String, Integer>("Juli", 81));
		series.getData().add(new XYChart.Data<String, Integer>("Augustus", 69));
		series.getData().add(new XYChart.Data<String, Integer>("September", 64));
		series.getData().add(new XYChart.Data<String, Integer>("Oktober", 49));
		series.getData().add(new XYChart.Data<String, Integer>("November", 37));
		series.getData().add(new XYChart.Data<String, Integer>("December", 50));

		return series;
	}
	
	private Series<String, Integer> legeSeries() {
		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		
		series.getData().add(new XYChart.Data<>("", 0));

		return series;
	}
}