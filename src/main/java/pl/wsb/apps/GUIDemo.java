package pl.wsb.apps;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class GUIDemo extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        TextField field = new TextField("");
        TextField name = new TextField("Name");
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.TOP_CENTER);
        Label label1 = new Label();
        label1.setText("Text");
        Label label0  = new Label("Text");
        Image icon = new Image("file:///c:/data/java11.jpg");
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(100);
        iconView.setPreserveRatio(true);
        Label label = new Label("Java. Podstawy", iconView);
        label.setContentDisplay(ContentDisplay.TOP);
        label.setLabelFor(root);
        label.setFont(Font.font("Lato Black", 20));
        Button okButton = new Button("OK");
        okButton.setDefaultButton(true);
        Button cancelButton = new Button("Cancel");
        cancelButton.setCancelButton(true);
        Button easyButton = new Button("Easy");
        easyButton.setOnAction(actionEvent -> {});
        CheckBox first = new CheckBox("First");
        CheckBox second = new CheckBox("Second");
        second.setAllowIndeterminate(true);
        second.isSelected();
        second.isIndeterminate();
        second.setSelected(true);
        Hyperlink searchLink = new Hyperlink("www.google.com");
        WebView browser = new WebView();
        searchLink.setOnAction( actionEvent ->
                browser.getEngine()
                        .load("https://" + searchLink.getText()));

        Label question = new Label("Wybierz ulubiony kolor?");
        Label answer = new Label("");
        ToggleGroup groupColors = new ToggleGroup();
        RadioButton radioRed = new RadioButton("Czerwony");
        RadioButton radioBlue = new RadioButton("Niebieski");
        RadioButton radioGreen = new RadioButton("Zielony");

        radioBlue.setUserData(Color.BLUE);
        radioRed.setUserData(Color.RED);
        radioGreen.setUserData(Color.GREEN);

        radioRed.setSelected(true);

        radioBlue.setToggleGroup(groupColors);
        radioRed.setToggleGroup(groupColors);
        radioGreen.setToggleGroup(groupColors);

        groupColors.selectedToggleProperty().addListener(listener -> answer.setText("Wybrałeś kolor " + groupColors.getSelectedToggle().getUserData()));

        MenuBar mainMenu = new MenuBar();
        Menu fileMenu = new Menu("_File");
        fileMenu.setMnemonicParsing(true);
        MenuItem openFileMenuItem = new MenuItem("Open...");
        openFileMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN));
        openFileMenuItem.setOnAction(e -> System.out.println("Input file name"));
        fileMenu.getItems().add(openFileMenuItem);
        mainMenu.getMenus().add(fileMenu);

        ContextMenu editMenu = new ContextMenu();
        editMenu.getItems().addAll(new MenuItem("Cut"), new MenuItem("Copy"), new MenuItem("Paste"));
        field.setContextMenu(editMenu);
        root.getChildren().addAll(field);
        Scene scene= new Scene(root,400,300);
        stage.setScene(scene);
        stage.show();
    }
}
