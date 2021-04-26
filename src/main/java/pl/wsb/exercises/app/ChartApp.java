package pl.wsb.exercises.app;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ChartApp extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        CategoryAxis products = new CategoryAxis();
        products.setLabel("produkty");
        NumberAxis numbers = new NumberAxis();
        numbers.setLabel("Liczba sprzedanych");
        BarChart<String, Number> chart = new BarChart<>(products, numbers);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().addAll(
                new XYChart.Data<>("telefony", 13),
                new XYChart.Data<>("laptopy", 5),
                new XYChart.Data<>("telewizory", 9)
        );
        chart.getData().add(series);
        chart.setTitle("Sprzedaż elektroniki");
        root.getChildren().add(chart);
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
}
