package pl.wsb.apps;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;


public class TableViewDemo extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        TableView<User> users = new TableView<>();
        TableColumn<User, String> names = new TableColumn<>("Nazwa");
        TableColumn<User, LocalDate> dates = new TableColumn<>("Data urodzin");
        TableColumn<User, Integer> points = new TableColumn<>("Punkty");
        users.getColumns().addAll(names, dates, points);

        names.setCellValueFactory(new PropertyValueFactory<>("name"));
        dates.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        points.setCellValueFactory(new PropertyValueFactory<>("points"));
        names.setCellFactory(TextFieldTableCell.forTableColumn());
        users.getItems().addAll(
                new User ("Adam", LocalDate.of(2000, 10, 4), 56),
                new User ("Ewa", LocalDate.of(2000, 11, 3), 134),
                new User ("Karol", LocalDate.of(2001, 4, 14), 101)
        );
        users.getSelectionModel().selectedItemProperty().addListener((list, userOld, userNew) -> {
            System.out.println(userNew);
        } );
        root.getChildren().add(users);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
