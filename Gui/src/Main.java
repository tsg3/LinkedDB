

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane GuiView;
    public static BuildingType tipo = new BuildingType();
    public static StoreList stores = new StoreList();
    public static TypeList types = new TypeList(stores.carpeta);

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("LinkedDB");

        initGuiView();

        showGui();
    }


    public void initGuiView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/GuiView.fxml"));
            GuiView = (BorderPane) loader.load();

            Scene scene = new Scene(GuiView);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showGui() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Gui.fxml"));
            AnchorPane Gui = (AnchorPane) loader.load();

            GuiView.setCenter(Gui);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}