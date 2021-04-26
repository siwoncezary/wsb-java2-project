package pl.wsb.exercises.app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import pl.wsb.App;

import java.io.BufferedReader;
import java.util.function.UnaryOperator;

public class FormApp extends Application {
    private WebView browser;
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        root.setSpacing(10);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(
                getLabel(),
                getOkButton(),
                getCancelButton(),
                getCheckBox(),
                getWebView(),
                getLink(),
                getToggleGroup(),
                getTextField(),
                getSpinner()
        );
        Scene scene = new Scene(root, 600,400);
        stage.setScene(scene);
        stage.show();
    }

    public Label getLabel(){
        Image image = new Image("https://impicode.pl/wp-content/uploads/2019/11/java-2.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
        Label label = new Label("Grafika", imageView);
        label.setContentDisplay(ContentDisplay.BOTTOM);
        return label;
    }

    public Button getOkButton(){
        Button ok = new Button("OK");
        ok.setDefaultButton(true);
        ok.setMinWidth(200);
        return ok;
    }

    public Button getCancelButton(){
        Button ok = new Button("_Anuluj");
        ok.setCancelButton(true);
        ok.setMinWidth(200);
        return ok;
    }

    public CheckBox getCheckBox(){
        CheckBox box = new CheckBox("Czy aktywny?");
        box.setSelected(false);
        box.setAllowIndeterminate(true);
        return box;
    }

    public Hyperlink getLink(){
        Hyperlink link = new Hyperlink("www.google.com");
        link.setOnAction(e -> {
            browser.getEngine().load("https://"+link.getText());
        });
        return link;
    }

    public WebView getWebView(){
        browser = new WebView();
        return browser;
    }

    public VBox getToggleGroup(){
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        ToggleGroup group = new ToggleGroup();
        RadioButton answer1 = new RadioButton("new");
        RadioButton answer2 = new RadioButton("super");
        RadioButton answer3 = new RadioButton("this");
        answer1.setUserData("new");
        answer2.setUserData("super");
        answer3.setUserData("this");
        answer1.setToggleGroup(group);
        answer2.setToggleGroup(group);
        answer3.setToggleGroup(group);
        Label userAnswer = new Label();
        group.selectedToggleProperty().addListener(e -> {
            userAnswer.setText("Użytkownik wybrał odpowiedź: " + group.getSelectedToggle().getUserData());
        });
        root.getChildren().addAll(answer1, answer2, answer3, userAnswer);
        return root;
    }

    public TextField getTextField(){
        TextField textField = new TextField();
        textField.setMinWidth(200);
        ContextMenu menu = new ContextMenu();
        MenuItem clear = new MenuItem("Clear");
        clear.setOnAction(e -> textField.setText(""));
        menu.getItems().addAll(
                new MenuItem("Copy"),
                new MenuItem("Paste"),
                new SeparatorMenuItem(),
                clear
        );
        textField.setContextMenu(menu);
        return textField;
    }

    public Spinner getSpinner(){
        Spinner<Integer> spinner = new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(-100, 100, 1));
        spinner.setEditable(true);
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            try {
                Integer.parseInt(newText);
                return change;
            } catch (NumberFormatException e){
                if (change.getControlNewText().isEmpty()){
                    return change;
                } else
                return null;
            }
        };
        TextFormatter<Integer> formatter = new TextFormatter<Integer>(new IntegerStringConverter(), 0, filter);
        spinner.getEditor().setTextFormatter(formatter);
        return spinner;
    }

}
