package pl.wsb.exercises.containers;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import pl.wsb.App;

public class ContainersDemo extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox();
        Label l1 = new Label("Etykieta 1");
        Label l2 = new Label("Etykieta 2");
        Label l3 = new Label("Etykieta 2");
        Label l4 = new Label("Etykieta 4");
        Label l5 = new Label("Etykieta 5");
        Label l6 = new Label("Etykieta 6");
        vBox.setPadding(new Insets(10, 20, 10, 20));
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(l1, l2, l3);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(l4, l5, l6);
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(10));
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(new Label("Etykieta w stack Pane"));
        GridPane gridPane = new GridPane();
        gridPane.add(vBox,1 ,1);
        gridPane.add(hBox, 1,2);
        gridPane.add(stackPane, 2, 1);
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(l1);
        borderPane.setCenter(l3);
        borderPane.setBottom(l4);
        borderPane.setTop(l5);
        Scene scene = new Scene(borderPane, 400, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
