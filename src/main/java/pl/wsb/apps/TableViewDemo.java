package pl.wsb.apps;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.FormatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.time.LocalDate;
import java.util.function.UnaryOperator;


public class TableViewDemo extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        //Application.setUserAgentStylesheet(BootstrapFX.bootstrapFXStylesheet());
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        TableView<User> users = new TableView<>();
        TableColumn<User, String> names = new TableColumn<>("Nazwa");
        TableColumn<User, LocalDate> dates = new TableColumn<>("Data urodzin");
        TableColumn<User, Integer> points = new TableColumn<>("Punkty");
        users.getColumns().addAll(names, dates, points);
        users.setEditable(true);
        names.setCellValueFactory(new PropertyValueFactory<>("name"));
        dates.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        points.setCellValueFactory(new PropertyValueFactory<>("points"));
        names.setCellFactory(ComboBoxTableCell.forTableColumn("Ala", "Tomek", "Staszek"));
        users.getItems().addAll(
                new User ("Adam", LocalDate.of(2000, 10, 4), 56),
                new User ("Ewa", LocalDate.of(2000, 11, 3), 134),
                new User ("Karol", LocalDate.of(2001, 4, 14), 101)
        );
        users.getSelectionModel().selectedItemProperty().addListener((list, userOld, userNew) -> {
            System.out.println(userNew);
        } );
        Spinner<Integer> spinner = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1));
        UnaryOperator<TextFormatter.Change> filter  = change -> {
            try {
                Integer.parseInt(change.getControlNewText());
                return change;
            } catch (NumberFormatException e){
                return null;
            }
        };
        TextFormatter<Integer> intFormatter = new TextFormatter<>(new IntegerStringConverter(), 0, filter);
        spinner.setEditable(true);
        spinner.getEditor().setTextFormatter(intFormatter);

        TextField textField = new TextField("Type");
        textField.setText("New text");
        textField.getText();
        StringProperty text = textField.textProperty();
        textField.setTextFormatter(new TextFormatter<>(new DefaultStringConverter(), "", change -> {
            if (change.getControlNewText().length() < 5){
                return change;
            } else {
                return null;
            }
        }));
        spinner.setStyle("-fx-text-fill: #FF;");
        root.getChildren().addAll(users, spinner);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
