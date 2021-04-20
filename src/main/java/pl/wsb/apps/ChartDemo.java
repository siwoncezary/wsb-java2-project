package pl.wsb.apps;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ChartDemo extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        CategoryAxis productsAxis = new CategoryAxis();
        productsAxis.setLabel("Products");
        NumberAxis numbersAxis = new NumberAxis();
        numbersAxis.setLabel("Products count");
        var chart = new BarChart<>(productsAxis, numbersAxis);
        var series = new XYChart.Series<String, Number>();
        series.getData().add(new XYChart.Data<>("Parasolki", 12.0));
        series.getData().add(new XYChart.Data<>("Kapcie", 16.0));
        series.getData().add(new XYChart.Data<>("Laptopy", 3.0));
        series.getData().add(new XYChart.Data<>("Telefony", 22.0));
        chart.getData().add(series);
        chart.setTitle("Sprzedaż produktów w kwietniu.");
        root.getChildren().add(chart);

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
