import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class InterfazTipoAtributo {

    public Button crear_atributo;
    public AnchorPane anchorPane;
    public TextField requerido;
    public TextField nombre_atributo;
    public TextField valor_por_defecto;

    public void atributo_ventana(ActionEvent actionEvent) throws IOException {
    	addAtributte(Main.tipo,(nombre_atributo.getText()),(valor_por_defecto.getText()),(requerido.getText()));
    	atributoVentana();
    }
    private void addAtributte(BuildingType tipo, String name, Object value, String requerido){
    	tipo.addAtributte(name,value,requerido);
    }
    private void atributoVentana() throws IOException{
        Stage stage = (Stage) crear_atributo.getScene().getWindow();
        stage.close();
        if (Main.tipo.pos<Main.tipo.atributtes.length){
        	FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/TypeAtributte.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root1));
            stage2.show();
        }
        else
        	Main.types.add(Main.tipo.atributtes,Main.tipo.valoresR,Main.tipo.valoresNR,Main.tipo.name);
    }

}