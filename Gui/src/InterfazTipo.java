import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.scene.control.TextField;
import java.io.IOException;

public class InterfazTipo {

    public Button crear_tipo;
    public AnchorPane anchorPane;
    public TextField definir_nombre;
    public TextField cantidad_atributos;

    private void tipoVentana() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/TypeAtributte.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        crear_tipo.setVisible(true);
        definir_nombre.setVisible(true);
        cantidad_atributos.setVisible(true);
    }

    public void tipo_ventana(ActionEvent actionEvent) throws IOException {
    	addName(Main.tipo,(definir_nombre.getText()));
    	numAtributtes(Main.tipo,Integer.parseInt(cantidad_atributos.getText()));
    	Main.tipo.createAtributtes();
    	Main.tipo.pos=0;
    	Main.tipo.valoresR=null;
    	Main.tipo.valoresNR=null;
    	tipoVentana();
    	closeButtonAction();
    }

    private void addName(BuildingType tipo, String name){
    	tipo.addName(name);
    }
    private void numAtributtes(BuildingType tipo, int num){
    	tipo.numAtributtes(num);
    }

    private void closeButtonAction(){
        Stage stage = (Stage) crear_tipo.getScene().getWindow();
        stage.close();
    }

}