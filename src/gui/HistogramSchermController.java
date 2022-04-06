package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;

public class HistogramSchermController extends GridPane {
	
	@FXML
	private AreaChart<String, Double> arcData;
	@FXML
	private BarChart<String, Double> brcData;
	@FXML
	private BubbleChart<Double, Double> blcData;
	@FXML
	private LineChart<String, Double> lncData;
	@FXML
	private PieChart pcData;
	@FXML
	private ScatterChart<String, Double> stcData;
	@FXML
	private StackedAreaChart<Double, Double> sacData;
	@FXML
	private StackedBarChart<String, Double> sbcData;
	
	private DomeinController dc;
	
	public HistogramSchermController(DomeinController dc) {
		this.dc = dc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HistogramScherm.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}	
		
		createAreaChart();
		createBarChart();
		createBubbleChart();
		createLineChart();
		createPieChart();
		createScatterChart();
		createStackedAreaChart();
		createStackedBarChart();
		
	}
	
	private void createAreaChart() {
		
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Mounth");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Value");
		
		XYChart.Series<String, Double> series = mockData();
		
		arcData.getData().add(series);
	}
	
	private void createBarChart() {
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Mounth");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Value");
		
		XYChart.Series<String, Double> series = mockData();
		
		brcData.getData().add(series);
	}
	
	private void createBubbleChart() {
		
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Mounth");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Value");
		
		XYChart.Series series1 = new XYChart.Series();
		
		series1.getData().add(new XYChart.Data(3, 35, 2));
		series1.getData().add(new XYChart.Data(12, 60, 1.8));
		series1.getData().add(new XYChart.Data(15, 15, 7));
		series1.getData().add(new XYChart.Data(22, 30, 2.5));
		series1.getData().add(new XYChart.Data(28, 20, 1));
		series1.getData().add(new XYChart.Data(35, 41, 5.5));
		series1.getData().add(new XYChart.Data(42, 17, 9));
		series1.getData().add(new XYChart.Data(49, 30, 1.8));
		
		blcData.getData().add(series1);
	}
	
	private void createLineChart() {
		
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Mounth");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Value");
		
		XYChart.Series<String, Double> series = mockData();
		
		lncData.getData().add(series);
	}
	
	private void createPieChart() {
		
		ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Grapefruit", 13),
                new PieChart.Data("Oranges", 25),
                new PieChart.Data("Plums", 10),
                new PieChart.Data("Pears", 22),
                new PieChart.Data("Apples", 30));
		
		pcData.setData(pieChartData);
	}
	
	private void createScatterChart() {
	
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Mounth");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Value");
		
		XYChart.Series<String, Double> series = mockData();
		
		stcData.getData().add(series);
	}
	
	private void createStackedAreaChart() {
		
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Mounth");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Value");
		
		XYChart.Series<Double, Double> series = new XYChart.Series();
		
		series.getData().add(new XYChart.Data( 0, 567));
		series.getData().add(new XYChart.Data( 1, 612));
		series.getData().add(new XYChart.Data( 2, 800));
		series.getData().add(new XYChart.Data( 3, 780));
		series.getData().add(new XYChart.Data( 4, 650));
		series.getData().add(new XYChart.Data( 5, 610));
		series.getData().add(new XYChart.Data( 6, 590));
		
		sacData.getData().add(series);
	}
	
	private void createStackedBarChart() {
		
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Mounth");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Value");
		
		XYChart.Series<String, Double> series = mockData();
		
		sbcData.getData().add(series);
	}

	private XYChart.Series<String, Double> mockData() {
		XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
		
		series.getData().add(new XYChart.Data<String, Double>("Januari", 100.0));
		series.getData().add(new XYChart.Data<String, Double>("Februari", 75.7));
		series.getData().add(new XYChart.Data<String, Double>("Maart", 33.3));
		series.getData().add(new XYChart.Data<String, Double>("April", 67.1));
		series.getData().add(new XYChart.Data<String, Double>("Mei", 98.2));
		series.getData().add(new XYChart.Data<String, Double>("Juni", 56.3));
		series.getData().add(new XYChart.Data<String, Double>("Juli", 78.1));
		series.getData().add(new XYChart.Data<String, Double>("Augustus", 89.9));
		series.getData().add(new XYChart.Data<String, Double>("September", 35.9));
		series.getData().add(new XYChart.Data<String, Double>("Oktober", 47.0));
		series.getData().add(new XYChart.Data<String, Double>("November", 91.0));
		series.getData().add(new XYChart.Data<String, Double>("December", 15.9));
		
		return series;
	}
	
}